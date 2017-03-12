package com.sorcererpaws.eSmartLabs.web.password;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sorcererpaws.eSmartLabs.core.entity.password.EmailLink;
import com.sorcererpaws.eSmartLabs.core.entity.user.User;
import com.sorcererpaws.eSmartLabs.core.entity.validation.ErrorMessage;
import com.sorcererpaws.eSmartLabs.core.entity.validation.ValidationResponse;
import com.sorcererpaws.eSmartLabs.core.service.global.GlobalService;
import com.sorcererpaws.eSmartLabs.core.service.mail.MailService;
import com.sorcererpaws.eSmartLabs.core.service.user.UserService;
import com.sorcererpaws.eSmartLabs.core.util.MailManager;
import com.sorcererpaws.eSmartLabs.core.validator.LoginFormValidator;


@Controller
public class PasswordController {

	@Autowired
	private LoginFormValidator loginFormValidator;

	@Autowired
	private MailManager mailManager;

	@Autowired
	private UserService userService;

	@Autowired
	private GlobalService globalService;

	@Autowired
	private MailService mailService;

	private static final Logger LOGGER = LoggerFactory.getLogger(PasswordController.class);

	@Secured(value = { "ROLE_LAB_ADMIN", "DOCTOR", "PATIENT" })
	@RequestMapping(value = "/user/{userId}/password", method = RequestMethod.GET)
	public ModelAndView changePasswordForm(@PathVariable("userId")long userId) {

		LOGGER.info("Change pasword form for userId: "+userId);
		return new ModelAndView("user/changePass").addObject("user", getUserService().getUser(userId));
	}

	@Secured(value = { "ROLE_LAB_ADMIN", "DOCTOR", "PATIENT" })
	@RequestMapping(value = "/user/password", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse changePassword(@ModelAttribute("user")User user, BindingResult result) {
		ValidationResponse response = new ValidationResponse();
		try {
			getLoginFormValidator().validateChangePasswordForm(user, result);
			if(result.hasErrors()) {
				
				LOGGER.info("Validation errors");
				
				response.setStatus("FAIL");
				List<FieldError> allErrors = result.getFieldErrors();
				List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
				for (FieldError objectError : allErrors) {
					errorMesages.add(new ErrorMessage(objectError.getField(), objectError.getDefaultMessage()));
				}
				response.setErrorMessageList(errorMesages);
			}else {

				if(getUserService().changePassword(user.getId(), user.getNewPassword())) {
					
					LOGGER.info(user.getName() + " : password changed successfully.");	
					response.setStatus("SUCCESS");
				} else {
					
					LOGGER.info("Error password changed successfully.");
					response.setStatus("FAIL");
				}
			}
		} catch (Exception exception) {

			exception.printStackTrace();
			LOGGER.info("Exception occured while changing password: "+exception.getMessage());
			response.setStatus("FAIL");
		}
		return response;
	}

	@RequestMapping(value = "/password/forgot", method = RequestMethod.GET)
	public ModelAndView resetPasswordInit() {
		ModelAndView modelAndView = new ModelAndView("password/forgotPass");
		modelAndView.addObject("user", new User());
		return modelAndView;
	}

	@RequestMapping(value = "/password/forgot.json", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse resetPasswordInitAction(@ModelAttribute("user")User user, BindingResult result, HttpServletRequest request) {
		ValidationResponse response = new ValidationResponse();
		try {
			getLoginFormValidator().validateForgotPassForm(user, result);
			if(result.hasErrors()) {
				response.setStatus("FAIL");
				List<FieldError> allErrors = result.getFieldErrors();
				List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
				for (FieldError objectError : allErrors) {
					errorMesages.add(new ErrorMessage(objectError.getField(), objectError.getDefaultMessage()));
				}
				response.setErrorMessageList(errorMesages);
			}else {
				user = getUserService().userByEmail(user.getEmail());
				EmailLink emailLink = getGlobalService().prepareEmailLink(user, request, 2222);
				if(emailLink != null) {
					boolean mailSent = getMailManager().sendResetPasswordLinkMail(user, emailLink);
					/*boolean mailSent = true;*/
					if(mailSent) {
						emailLink = getMailService().addEmailLink(emailLink);
						if(emailLink.getId() > 0) {
							response.setStatus("SUCCESS");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@RequestMapping(value = "/password/reset", method = RequestMethod.GET)
	public ModelAndView resetPasswordPage(@Param long userId, @Param String otrt) {
		ModelAndView modelAndView = null;
		String code = null;
		if(userId > 0 && otrt != null) {
			if(!otrt.isEmpty()) {
				User user = getUserService().getUser(userId);
				if(user != null) {
					if(user.isEnabled()) {
						EmailLink emailLink = getMailService().getEmailLinkByUserAndToken(userId, otrt);
						if(emailLink != null) {
							if(emailLink.isActive()) {
								if(getGlobalService().isLinkExpired(emailLink.getCreated())) {
									//Link Expired
									emailLink.setExpired(getGlobalService().getLinkExpiredDate(emailLink.getCreated()));
									emailLink.setActive(false);
									emailLink = getMailService().updateEmailLink(emailLink);
									modelAndView = new ModelAndView("password/reset");
									code = "EXP";
									modelAndView.addObject("eCode", code);
								}else {
									//Link Used
									emailLink.setActive(false);
									emailLink = getMailService().updateEmailLink(emailLink);
									modelAndView = new ModelAndView("password/reset");
									user.setPassword("");
									user.setConfirmPassword("");
									modelAndView.addObject("user", user);
								}	
							}else {
								//Link Inactive
								modelAndView = new ModelAndView("password/error");
								code = "USED";
								modelAndView.addObject("eCode", code);
							}
						}else {
							//Link Not Found
							modelAndView = new ModelAndView("password/error");
							code = "NF";
							modelAndView.addObject("eCode", code);
						}
					}else {
						//User blocked
						modelAndView = new ModelAndView("password/error");
						code = "UBLO";
						modelAndView.addObject("eCode", code);
					}
				}else {
					//User NF
					modelAndView = new ModelAndView("password/error");
					code = "UNF";
					modelAndView.addObject("eCode", code);
				}
			}else {
				//Invalid Link
				modelAndView = new ModelAndView("password/error");
				code = "IL";
				modelAndView.addObject("eCode", code);
			}
		}else {
			//Invalid Link
			modelAndView = new ModelAndView("password/error");
			code = "IL";
			modelAndView.addObject("eCode", code);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/password/reset.json", method = RequestMethod.POST)
	public @ResponseBody ValidationResponse resetPassword(@ModelAttribute("user")User user, BindingResult result, HttpServletRequest request) {
		ValidationResponse response = new ValidationResponse();
		try {
			getLoginFormValidator().validateResetPasswordForm(user, result);
			if(result.hasErrors()) {
				LOGGER.info("Validation errors while resetting password.");
				response.setStatus("FAIL");
				List<FieldError> allErrors = result.getFieldErrors();
				List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
				for (FieldError objectError : allErrors) {
					errorMesages.add(new ErrorMessage(objectError.getField(), objectError.getDefaultMessage()));
				}
				response.setErrorMessageList(errorMesages);
			}else {
				if(getUserService().changePassword(user.getId(), user.getNewPassword())) {
					LOGGER.info("Password reset success.");
					response.setStatus("SUCCESS");
				} else {
					LOGGER.info("Password reset failed: "+user.getId());
					response.setStatus("FAIL");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("Exception while resetting password: "+user.getId());
			response.setStatus("FAIL");
		}
		return response;
	}

	//Getters and setters
	public LoginFormValidator getLoginFormValidator() {
		return loginFormValidator;
	}

	public void setLoginFormValidator(LoginFormValidator loginFormValidator) {
		this.loginFormValidator = loginFormValidator;
	}

	public MailManager getMailManager() {
		return mailManager;
	}

	public void setMailManager(MailManager mailManager) {
		this.mailManager = mailManager;
	}

	public GlobalService getGlobalService() {
		return globalService;
	}

	public void setGlobalService(GlobalService globalService) {
		this.globalService = globalService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}
}
