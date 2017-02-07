package com.sorcererpaws.eSmartLabs.web.company;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sorcererpaws.eSmartLabs.core.entity.common.Address;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Company;
import com.sorcererpaws.eSmartLabs.core.entity.validation.ErrorMessage;
import com.sorcererpaws.eSmartLabs.core.entity.validation.ValidationResponse;
import com.sorcererpaws.eSmartLabs.core.service.company.CompanyService;
import com.sorcererpaws.eSmartLabs.core.service.geo.GeoService;
import com.sorcererpaws.eSmartLabs.core.service.user.UserService;
import com.sorcererpaws.eSmartLabs.core.validator.CompanyValidator;

@RestController
public class CompanyRestClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyRestClient.class);

	@Autowired
	private CompanyService companyService;
	@Autowired
	private UserService userService;
	@Autowired
	private GeoService geoService;

	@RequestMapping(value = "/company/update", method = RequestMethod.POST)
	public ResponseEntity<ValidationResponse> updateCompany(@ModelAttribute("company")Company company, BindingResult bindingResult) {
		ValidationResponse response = new ValidationResponse();
		CompanyValidator companyValidator = new CompanyValidator(); 
		try {
			companyValidator.validateCompany(company, bindingResult);
			
			if(bindingResult.hasErrors()) {

				List<FieldError> allErrors = bindingResult.getFieldErrors();
				List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
				for (FieldError objectError : allErrors) {
					errorMesages.add(new ErrorMessage(objectError.getField(), objectError.getDefaultMessage()));
				}

				response.setErrorMessageList(errorMesages);
				response.setStatus("FAIL");
				LOGGER.info("Error while creating/updating doctor.");
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.BAD_REQUEST);
			}else {
				
				LOGGER.info("Updating company address...");
				Address address = getGeoService().updateAddress(company.getAddress());
				LOGGER.info("..company address updated.");
				company.setAddress(address);
				LOGGER.info("Updating company...");
				company = getCompanyService().updateCompany(company);
				LOGGER.info("company updated.");
				
				response.setObject(company);
				response.setStatus("SUCCESS");
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			LOGGER.info("Error while creating/updating company: "+e.getMessage());
			response.setStatus("FAIL");
			return new ResponseEntity<ValidationResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	@RequestMapping(value = "/company/{companyId}", method = RequestMethod.GET)
	public ResponseEntity<Company> getCompany(@PathVariable("companyId")Long companyId) {
		Company company = getCompanyService().getCompany(companyId);
		if(company == null) {
			
			return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Company>(company, HttpStatus.OK);
	}

	//Getters and setters
	public CompanyService getCompanyService() {
		return companyService;
	}
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public GeoService getGeoService() {
		return geoService;
	}
	public void setGeoService(GeoService geoService) {
		this.geoService = geoService;
	}


}
