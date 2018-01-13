package com.sorcererpaws.eSmartLabs.web.login;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Company;
import com.sorcererpaws.eSmartLabs.core.entity.user.Role;
import com.sorcererpaws.eSmartLabs.core.entity.user.User;
import com.sorcererpaws.eSmartLabs.core.entity.validation.ErrorMessage;
import com.sorcererpaws.eSmartLabs.core.entity.validation.ValidationResponse;
import com.sorcererpaws.eSmartLabs.core.service.company.CompanyService;
import com.sorcererpaws.eSmartLabs.core.service.global.GlobalService;
import com.sorcererpaws.eSmartLabs.core.service.mail.MailService;
import com.sorcererpaws.eSmartLabs.core.service.user.UserService;
import com.sorcererpaws.eSmartLabs.core.util.MailManager;
import com.sorcererpaws.eSmartLabs.core.validator.LoginFormValidator;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	@Autowired
	private GlobalService globalService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private MailService mailService;
	@Autowired
	private MailManager mailManager;
	@Autowired
	private LoginFormValidator loginFormValidator;

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	/**
	 * Used to get the login page.
	 * @param user
	 * @param logout
	 * @param sessionExp
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView LoginPage(@RequestParam(value = "logout" , required = false)String logout, @RequestParam(value = "sessionExp" , required = false)String sessionExp, Principal principal) {

		ModelAndView modelAndView =  new ModelAndView();

		if(getGlobalService().isAdminPresent()){
			if(principal == null) {
				modelAndView.addObject("user", new User());
				if(logout != null){
					logout = "You Have Been Successfully Logged Out";
					modelAndView.addObject("logout", logout);
				}

				if(sessionExp != null){
					sessionExp = "Your Session Expired! Login Again";
					modelAndView.addObject("sessionExp", sessionExp);
				}
				modelAndView.setViewName("home/pages/login");
			}else {
				modelAndView.setViewName("redirect:/");
			}
		}else{
			modelAndView.setViewName("redirect:/register");
		}
		return modelAndView;
	}

	/**
	 * Validates the login form.
	 * @param user
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/login.json", method = RequestMethod.POST)
	public ResponseEntity<ValidationResponse> validateLoginForm(@ModelAttribute("appUser")User appUser, BindingResult result) {
		ValidationResponse response = new ValidationResponse();
		try {
			getLoginFormValidator().validateLoginForm(appUser, result);
			if(result.hasErrors()) {
				
				List<FieldError> allErrors = result.getFieldErrors();
				List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
				for (FieldError objectError : allErrors) {
					errorMesages.add(new ErrorMessage(objectError.getField(), objectError.getDefaultMessage()));
				}
				
				response.setErrorMessageList(errorMesages);
				response.setStatus("FAIL");
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.BAD_REQUEST);
			}else {
				response.setStatus("SUCCESS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			LOGGER.info("Error while logging - in: "+e.getMessage());
			response.setStatus("FAIL");
			return new ResponseEntity<ValidationResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setObject(appUser);
		response.setStatus("SUCCESS");
		return new ResponseEntity<ValidationResponse>(response, HttpStatus.OK);
	}

	/**
	 * Used to redirect the authenticated user after security login.
	 * @param principal
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/postLoginAction" , method = RequestMethod.GET)
	public String processLoginAction(Authentication authentication, HttpSession httpSession) {

		User loggedInUser = null;
		if(authentication != null){

			loggedInUser = getUserService().userByEmail(authentication.getName());
			if(loggedInUser != null) {

				LOGGER.info("logged in user : "+authentication.getName());
				httpSession.setAttribute("loggedInUser", loggedInUser);
			}

			boolean isAdmin = false;
			boolean isDoctor = false;
			boolean isTechnician = false;
			boolean isPatient = false;
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			for (GrantedAuthority grantedAuthority : authorities) {
				if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
					isAdmin = true;
					break;
				} else if (grantedAuthority.getAuthority().equals("ROLE_TECHNICIAN")) {
					isTechnician = true;
					break;
				} else if(grantedAuthority.getAuthority().equals("ROLE_DOCTOR")) {
					isDoctor = true;
					break;
				} else if(grantedAuthority.getAuthority().equals("ROLE_PATIENT")) {
					isPatient = true;
					break;
				}
			}

			if (isAdmin) {

				return "redirect:/admin/dashboard";
			} else if (isTechnician) {

				return "redirect:/technician/dashboard";
			} else if(isDoctor){

				return "redirect:/doctor/dashboard";
			} else if(isPatient){

				return "redirect:/patient/dashboard";
			} else {

				throw new IllegalStateException();
			}

		}else {

			LOGGER.info("Authentication failed.");
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/doctor/dashboard", method = RequestMethod.GET)
	public String doctorDashboard() {
		return "doctor/dashboard";
	}

	@RequestMapping(value = "/patient/dashboard", method = RequestMethod.GET)
	public String patientDashboard() {
		return "patient/dashboard";
	}

	@RequestMapping(value = "/technician/dashboard", method = RequestMethod.GET)
	public String technicianDashboard() {
		return "technician/dashboard";
	}

	@RequestMapping(value = "/admin/dashboard", method = RequestMethod.GET)
	public String adminDashboard() {
		return "admin/dashboard";
	}

	/**
	 * Used to get the ADMIN register page.
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String adminRegistrationPage(ModelMap modelMap) {

		if(getGlobalService().isAdminPresent()){
			return "redirect:/login";
		}else {
			modelMap.addAttribute("user", new User());
			return "home/pages/register";
		}
	}

	/**
	 * Used to validate and process the ADMIN registration action.
	 * @param user
	 * @param errors
	 * @param servletResponse
	 * @param servletRequest
	 * @return
	 */
	/*@RequestMapping(value = "/register.json", method = RequestMethod.POST)
	@ResponseBody ValidationResponse addFaculty(@ModelAttribute("appUser")User user, BindingResult errors, HttpServletResponse servletResponse, HttpServletRequest servletRequest) {

		ValidationResponse response = new ValidationResponse();
		try {

			getLoginFormValidator().validate(user, errors);
			if(errors.hasErrors()) {
				response.setStatus("FAIL");
				List<FieldError> allErrors = errors.getFieldErrors();
				List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
				for (FieldError objectError : allErrors) {
					errorMesages.add(new ErrorMessage(objectError.getField(), objectError.getDefaultMessage()));
				}
				response.setErrorMessageList(errorMesages);
			}else {

				Role role = new Role();
				role.setId(new Long(1));
				user.setRole(role);

				user.setCreatedOn(new Date());	

				User savedUser = getUserService().addUser(user);
				if(savedUser.getId() != null) {

					Company company = new Company();
					company.setId(new Long(1));
					company.setName("My Company");
					company.setUser(user);

					LOGGER.info("Creating default company profile....");
					getCompanyService().addCompany(company);
					LOGGER.info("... company profile created.");

					boolean mailSent = true;
					if(mailSent){
						response.setStatus("SUCCESS");
					}
				} else {
					
					response.setStatus("FAIL");
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			response.setStatus("FAIL");
		}
		
		return response;
	}*/
	
	@RequestMapping(value = "/register.json", method = RequestMethod.POST)
	public ResponseEntity<ValidationResponse> createAdmin(@ModelAttribute("user")User user, BindingResult bindingResult, HttpServletRequest request) {
		
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
				LOGGER.info("Error while registering user.");
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.BAD_REQUEST);
			}else {
				
				Role role = new Role();
				role.setId(new Long(1));
				user.setRole(role);

				user.setCreatedOn(new Date());	

				User savedUser = getUserService().addUser(user);
				if(savedUser.getId() != null) {

					Company company = new Company();
					company.setId(new Long(1));
					company.setName("My Company");
					company.setUser(user);

					LOGGER.info("Creating default company profile....");
					getCompanyService().addCompany(company);
					LOGGER.info("... company profile created.");

					boolean mailSent = true;
					if(mailSent) {
						
						response.setStatus("SUCCESS");
					}
				} else {
					
					response.setStatus("FAIL");
				}
				
				response.setObject(user);
				response.setStatus("SUCCESS");
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			LOGGER.info("Error while registering user: " + e.getMessage());
			response.setStatus("FAIL");
			return new ResponseEntity<ValidationResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}

	/**
	 * Used to activate the user.
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/confirmEmail" , method = RequestMethod.GET)
	public String confirmUser(@Param long userId) {
		if(userId > 0) {
			if(getUserService().activateUser(userId)){
				return "redirect:/login";
			}
		}
		return "redirect:/home";
	}

	//Getters and setters
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public GlobalService getGlobalService() {
		return globalService;
	}

	public void setGlobalService(GlobalService globalService) {
		this.globalService = globalService;
	}

	public CompanyService getCompanyService() {
		return companyService;
	}

	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public MailManager getMailManager() {
		return mailManager;
	}

	public void setMailManager(MailManager mailManager) {
		this.mailManager = mailManager;
	}

	public LoginFormValidator getLoginFormValidator() {
		return loginFormValidator;
	}

	public void setLoginFormValidator(LoginFormValidator loginFormValidator) {
		this.loginFormValidator = loginFormValidator;
	}
}
