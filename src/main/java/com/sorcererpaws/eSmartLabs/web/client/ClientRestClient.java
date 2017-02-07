package com.sorcererpaws.eSmartLabs.web.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Client;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Company;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Lab;
import com.sorcererpaws.eSmartLabs.core.entity.user.Role;
import com.sorcererpaws.eSmartLabs.core.entity.user.User;
import com.sorcererpaws.eSmartLabs.core.entity.validation.ErrorMessage;
import com.sorcererpaws.eSmartLabs.core.entity.validation.ValidationResponse;
import com.sorcererpaws.eSmartLabs.core.service.client.ClientService;
import com.sorcererpaws.eSmartLabs.core.service.lab.LabService;
import com.sorcererpaws.eSmartLabs.core.service.user.UserService;
import com.sorcererpaws.eSmartLabs.core.validator.CompanyValidator;

@RestController
public class ClientRestClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientRestClient.class);
	@Autowired
	private ClientService clientService;
	@Autowired
	private LabService labService;
	@Autowired
	private UserService userService;
	@Autowired
	private CompanyValidator companyValidator;
	
	
	@RequestMapping(value = "/client/register", method = RequestMethod.POST)
	public ResponseEntity<ValidationResponse> updateCompany(@ModelAttribute("client")Client client, BindingResult bindingResult) {
		ValidationResponse response = new ValidationResponse();
		try {
			
			getCompanyValidator().validateClientRegister(client, bindingResult);
			
			if(bindingResult.hasErrors()) {

				List<FieldError> allErrors = bindingResult.getFieldErrors();
				List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
				for (FieldError objectError : allErrors) {
					errorMesages.add(new ErrorMessage(objectError.getField(), objectError.getDefaultMessage()));
				}

				response.setErrorMessageList(errorMesages);
				response.setStatus("FAIL");
				LOGGER.info("Error while registering client.");
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.BAD_REQUEST);
			}else {
				
				User user = client.getUser();
				Role role = new Role();
				role.setId(new Long(2));
				user.setRole(role);
				user.setName("Your Name");
				user.setCreatedOn(new Date());
				user.setLastModified(new Date());
				user.setEnabled(false);
				
				LOGGER.info("adding client user...");
				user = getUserService().addUser(user);
				LOGGER.info("user added.");
				
				client.setUser(user);
				
				Lab lab = client.getLab();
				LOGGER.info("adding client lab...");
				lab = getLabService().updateLab(lab);
				LOGGER.info("lab added.");
				
				client.setLab(lab);
				
				Company company = new Company();
				company.setId(new Long(1));
				client.setCompany(company);
				
				LOGGER.info("adding client...");
				client = getClientService().addClient(client);
				LOGGER.info("client added.");
				
				response.setObject(client);
				response.setStatus("SUCCESS");
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			LOGGER.info("Error while registering client: "+e.getMessage());
			response.setStatus("FAIL");
			return new ResponseEntity<ValidationResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}

	
	//Getters and setters
	public ClientService getClientService() {
		return clientService;
	}


	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}


	public LabService getLabService() {
		return labService;
	}


	public void setLabService(LabService labService) {
		this.labService = labService;
	}


	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public CompanyValidator getCompanyValidator() {
		return companyValidator;
	}


	public void setCompanyValidator(CompanyValidator companyValidator) {
		this.companyValidator = companyValidator;
	}
}
