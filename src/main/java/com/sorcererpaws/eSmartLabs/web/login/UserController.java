package com.sorcererpaws.eSmartLabs.web.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sorcererpaws.eSmartLabs.core.entity.user.User;
import com.sorcererpaws.eSmartLabs.core.service.user.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Secured(value = { "ROLE_LAB_ADMIN", "DOCTOR", "PATIENT" })
	@RequestMapping(value = "/user/{userId}/account", method = RequestMethod.GET)
	public ModelAndView getUser(@PathVariable("userId") long userId) {
		User user = getUserService().getUser(userId);
		if(user == null) {
			return new ModelAndView("redirect:pageNotFound");
		} else {
			return new ModelAndView("user/view").addObject("user", user);
		}
	}
	
	@Secured(value = { "ROLE_LAB_ADMIN" })
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ModelAndView getUsers() {
		return new ModelAndView("user/display");
	}
	
	@Secured(value = { "ROLE_LAB_ADMIN", "DOCTOR", "PATIENT" })
	@RequestMapping(value = "/user/{userId}/update", method = RequestMethod.GET)
	public ModelAndView updateUser(@PathVariable("userId")long userId) {
		User appUser = getUserService().getUser(userId);
		if(appUser == null) {
			return new ModelAndView("redirect:pageNotFound");
		} else {
			return new ModelAndView("user/edit").addObject("user", appUser);
		}
	}
	
	@Secured(value = { "ROLE_LAB_ADMIN" })
	@RequestMapping(value = "/user/{userId}/activate", method = RequestMethod.GET)
	public String activateDeactivateUser(@PathVariable("userId")long userId) {
		User appUser = getUserService().getUser(userId);
		return (appUser == null ? "redirect:pageNotFound" 
				: (appUser.isEnabled() ? getUserService().deActivateUser(userId) 
						: getUserService().activateUser(userId)) ? "redirect:/user/"+userId+"/account" 
								: "redirect:pageNotFound");
	}
	
	//Getters and setters
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
