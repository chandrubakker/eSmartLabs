package com.sorcererpaws.eSmartLabs.core.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Client;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Company;
import com.sorcererpaws.eSmartLabs.core.service.user.UserService;

@Component
public class CompanyValidator implements Validator {

	@Autowired
	private UserService userService;
	private Pattern pattern;
	private Matcher matcher;
	private static String EMAIL = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

	private boolean patternMatcher(String input,String regexPattern){
		boolean matched = false;
		if(!input.isEmpty() && input != null){
			pattern = Pattern.compile(regexPattern);
			matcher = pattern.matcher(input);
			if(matcher.matches()){
				matched = true;
			}
		}
		return matched;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub

	}

	public void validateCompany(Company company, Errors errors) {

		if(company.getName().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "noCode", "enter company name");
		}

		if(company.getAddress().getCity().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.city", "noCode", "enter company name");
		}
	}

	public void validateClientRegister(Client client, Errors errors) {

		if(client.getLab().getName().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lab.name", "noCode", "enter laboratory name");
		}

		if(client.getUser().getEmail().isEmpty()){
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.email", "noCode","enter email");
		}else if (!patternMatcher(client.getUser().getEmail(), EMAIL)) {
			
			errors.rejectValue("user.email", "noCode", "Enter valid email");
		}else if(!client.getUser().getEmail().isEmpty()) {
			
			if(getUserService().userExists(client.getUser().getEmail())){
				errors.rejectValue("user.email", "noCode", "user with the entered email already exists");
			}
		}

		if(client.getUser().getPassword().isEmpty()){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.password", "noCode","enter password");
		}else if (client.getUser().getPassword().length() < 8) {
			errors.rejectValue("user.password", "noCode", "password must contain min. 8 characters");
		}else if (client.getUser().getPassword().length() > 40) {
			errors.rejectValue("user.password", "noCode", "too long password");
		}

		if(client.getUser().getConfirmPassword().isEmpty()){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.confirmPassword", "noCode","enter password again");
		}else if (!client.getUser().getConfirmPassword().equals(client.getUser().getPassword()) && !client.getUser().getPassword().isEmpty()) {
			errors.rejectValue("user.confirmPassword", "noCode", "both passwords are not matching");
		}
	}

	//Getters and setters
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
