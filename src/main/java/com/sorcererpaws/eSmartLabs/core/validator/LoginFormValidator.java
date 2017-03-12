package com.sorcererpaws.eSmartLabs.core.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sorcererpaws.eSmartLabs.core.entity.user.User;
import com.sorcererpaws.eSmartLabs.core.service.user.UserService;

@Component
public class LoginFormValidator implements Validator {

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

	/**
	 * Used to validate user registration form.
	 * @param target
	 * @param errors
	 */
	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;

		if(user.getName().isEmpty()){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "noCode","enter name");
		}else if (user.getName().length() > 35) {
			errors.rejectValue("name", "noCode", "name should not exceed 35 characters");
		}

		if(user.getEmail().isEmpty()){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "noCode","enter email");
		}else if (!patternMatcher(user.getEmail(), EMAIL)) {
			errors.rejectValue("email", "noCode", "Enter valid email");
		}else if(!user.getEmail().isEmpty()) {
			boolean userExists = false;
			try {
				userExists = getUserService().userExists(user.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(userExists){
				errors.rejectValue("email", "noCode", "user with the entered email already exists");
			}
		}
		
		if(user.getPassword().isEmpty()){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "noCode","enter password");
		}else if (user.getPassword().length() < 8) {
			errors.rejectValue("password", "noCode", "password must contain min. 8 characters");
		}else if (user.getPassword().length() > 40) {
			errors.rejectValue("password", "noCode", "too long password");
		}

		if(user.getConfirmPassword().isEmpty()){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "noCode","enter password again");
		}else if (!user.getConfirmPassword().equals(user.getPassword()) && !user.getPassword().isEmpty()) {
			errors.rejectValue("confirmPassword", "noCode", "password are not matching");
		}
	}
	
	/**
	 * Used to validate user update form.
	 * @param user
	 * @param errors
	 */
	public void validateUserUpdate(User user, Errors errors) {
		if(user.getName().isEmpty()){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "noCode","enter name");
		}else if (user.getName().length() > 35) {
			errors.rejectValue("name", "noCode", "name should not exceed 35 characters");
		}

		if(user.getEmail().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "noCode","enter email");
		}else if (!patternMatcher(user.getEmail(), EMAIL)) {
			errors.rejectValue("email", "noCode", "Enter valid email");
		}else if(!user.getEmail().isEmpty()) {
			User existingUser = getUserService().getUser(user.getId());
			if(existingUser.getEmail() == user.getEmail()) {
				boolean userExists = false;
				try {
					userExists = getUserService().userExists(user.getEmail());
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(userExists){
					errors.rejectValue("email", "noCode", "user with the entered email already exists");
				}
			}
		}
	}
	
	/**
	 * Used to validate the login form
	 * @param appUser
	 * @param errors
	 */
	public void validateLoginForm(User appUser, Errors errors) {
		User existingUser = null;

		if(appUser.getEmail().isEmpty()){
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "noCode","email address required");
		}else if(!patternMatcher(appUser.getEmail(), EMAIL)){
			
			errors.rejectValue("email", "noCode", "invalid email address");
		} else if(!getUserService().userExists(appUser.getEmail())) {
			
			errors.rejectValue("email", "noCode", "user with this email address not registered");
		} else {
			
			existingUser = getUserService().userByEmail(appUser.getEmail());
			if(existingUser == null) {
				
				errors.rejectValue("email", "noCode", "user with this email address not registered");
			} else if(!existingUser.isEnabled()){
				
				errors.rejectValue("enabled", "noCode", "Your account not activated, contact site admin/use account activation link");
			} else if(appUser.getPassword().isEmpty()) {
				
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "noCode","enter password");
			} else {
				
				if(!BCrypt.checkpw(appUser.getPassword(), existingUser.getPassword()) && existingUser.isEnabled()) {
					errors.rejectValue("password", "noCode", "incorrect password");
				}
			}
		}
	}
	
	/**
	 * Used to validate the change password form.
	 * @param formUser
	 * @param errors
	 */
	public void validateChangePasswordForm(User formUser, Errors errors) {

		if(formUser.getPassword().isEmpty()){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "noCode","enter old password");
		}else if(!formUser.getPassword().isEmpty() && formUser.getId() > 0) {
			User existingUser = getUserService().getUser(formUser.getId());
			if(existingUser != null) {
				if(!BCrypt.checkpw(formUser.getPassword(), existingUser.getPassword())) {
					errors.rejectValue("password", "noCode", "incorrect old password");
				}
			}
		}

		if(formUser.getNewPassword().isEmpty()){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", "noCode","enter new password");
		}else if (formUser.getNewPassword().length() < 8) {
			errors.rejectValue("newPassword", "noCode", "password must contain min. 8 characters");
		}

		if(formUser.getConfirmPassword().isEmpty()){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "noCode","enter new password again");
		}else if (!formUser.getConfirmPassword().equals(formUser.getNewPassword()) && !formUser.getNewPassword().isEmpty()) {
			errors.rejectValue("confirmPassword", "noCode", "password mismatch");
		}
	}
	
	public void validateForgotPassForm(User user, Errors errors) {
		
		User existingUser = null;

		if(user.getEmail().isEmpty()){
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "noCode","email address required");
		}else if(!patternMatcher(user.getEmail(), EMAIL)){
			
			errors.rejectValue("email", "noCode", "invalid email address");
		} else if(!getUserService().userExists(user.getEmail())) {
			
			errors.rejectValue("email", "noCode", "user with this email address not registered");
		} else {
			
			existingUser = getUserService().userByEmail(user.getEmail());
			if(existingUser == null) {
				
				errors.rejectValue("email", "noCode", "user with this email address not registered");
			} else if(!existingUser.isEnabled()){
				
				errors.rejectValue("enabled", "noCode", "Your account not activated, contact site admin/use account activation link");
			}
		}
	}
	
	public void validateResetPasswordForm(User formUser, Errors errors) {
		if(formUser.getNewPassword().isEmpty()){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", "noCode","enter new password");
		}else if (formUser.getNewPassword().length() < 8) {
			errors.rejectValue("newPassword", "noCode", "password must contain min. 8 characters");
		}

		if(formUser.getConfirmPassword().isEmpty()){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "noCode","enter new password again");
		}else if (!formUser.getConfirmPassword().equals(formUser.getNewPassword()) && !formUser.getNewPassword().isEmpty()) {
			errors.rejectValue("confirmPassword", "noCode", "password mismatch");
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
