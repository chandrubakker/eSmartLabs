package com.sorcererpaws.eSmartLabs.web.login;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sorcererpaws.eSmartLabs.core.entity.user.User;
import com.sorcererpaws.eSmartLabs.core.entity.validation.ErrorMessage;
import com.sorcererpaws.eSmartLabs.core.entity.validation.ValidationResponse;
import com.sorcererpaws.eSmartLabs.core.service.user.UserService;
import com.sorcererpaws.eSmartLabs.core.validator.LoginFormValidator;

@RestController
public class UserRestClient {

	private final static Logger LOGGER = LoggerFactory.getLogger(UserRestClient.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginFormValidator loginFormValidator;
	
	@RequestMapping(value = "/user/create", method = RequestMethod.POST)
	public ResponseEntity<ValidationResponse> createUser(@ModelAttribute("user")User user, BindingResult bindingResult) {
		
		ValidationResponse response = new ValidationResponse();
		try {
			
			getLoginFormValidator().validate(user, bindingResult);
			if(bindingResult.hasErrors()) {
				
				List<FieldError> allErrors = bindingResult.getFieldErrors();
				List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
				for (FieldError objectError : allErrors) {
					errorMesages.add(new ErrorMessage(objectError.getField(), objectError.getDefaultMessage()));
				}
				response.setErrorMessageList(errorMesages);
				response.setStatus("FAIL");
				LOGGER.info("Error while creating user.");
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.OK);
			}else {
				
				user.setCreatedOn(new Date());
				user = getUserService().addUser(user);
				response.setObject((User) user);
				response.setStatus("SUCCESS");
				LOGGER.info("User created.");
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			LOGGER.info("Error while creating user: "+e.getMessage());
			response.setStatus("FAIL");
			return new ResponseEntity<ValidationResponse>(response, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public ResponseEntity<ValidationResponse> updateUser(@ModelAttribute("user")User user, BindingResult bindingResult) {
		
		ValidationResponse response = new ValidationResponse();
		try {
			
			getLoginFormValidator().validateUserUpdate(user, bindingResult);
			if(bindingResult.hasErrors()) {
				
				List<FieldError> allErrors = bindingResult.getFieldErrors();
				List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
				for (FieldError objectError : allErrors) {
					errorMesages.add(new ErrorMessage(objectError.getField(), objectError.getDefaultMessage()));
				}
				response.setErrorMessageList(errorMesages);
				response.setStatus("FAIL");
				LOGGER.info("Error while creating user.");
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.OK);
			}else {
				
				User existingUser = getUserService().getUser(user.getId());
				existingUser.setName(user.getName());
				existingUser.setEmail(user.getEmail());
				
				user = getUserService().updateUser(existingUser);
				response.setObject((User) user);
				response.setStatus("SUCCESS");
				LOGGER.info("User updated.");
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			LOGGER.info("Error while creating user: "+e.getMessage());
			response.setStatus("FAIL");
			return new ResponseEntity<ValidationResponse>(response, HttpStatus.OK);
		}
	}
	
	@Secured(value = { "ROLE_LAB_ADMIN", "DOCTOR", "PATIENT" })
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("userId")long userId) {
		
		User user = getUserService().getUser(userId);
		
		return (user == null ? new ResponseEntity<User>(HttpStatus.NOT_FOUND) : 
			new ResponseEntity<User>(user, HttpStatus.OK));
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Secured(value = { "ROLE_LAB_ADMIN" })
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("userId")long userId) {
		
		User user = getUserService().getUser(userId);
		
		return (user == null ? new ResponseEntity(HttpStatus.NOT_FOUND) 
				: (getUserService().deActivateUser(userId) ? new ResponseEntity<User>(HttpStatus.NO_CONTENT) 
						: new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)));
	}
	
	@Secured(value = { "ROLE_LAB_ADMIN" })
	@RequestMapping(value = "/users.json", method = RequestMethod.GET)
	public ResponseEntity<List<User>> allUsers() {
		
		return new ResponseEntity<List<User>>(getUserService().users(), HttpStatus.OK);
	}

	//Getters and setters
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public LoginFormValidator getLoginFormValidator() {
		return loginFormValidator;
	}

	public void setLoginFormValidator(LoginFormValidator loginFormValidator) {
		this.loginFormValidator = loginFormValidator;
	}
}
