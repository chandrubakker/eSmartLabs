package com.sorcererpaws.eSmartLabs.web.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sorcererpaws.eSmartLabs.core.service.global.GlobalService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private GlobalService globalService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homeOne() {
		LOGGER.info("loading home");
		if(!getGlobalService().isAdminPresent()){
			LOGGER.info("Admin not found redirecting to registration page.");
			return "redirect:/register";
		} else {
			return "home/pages/home";
		}
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView homeTwo() {
		LOGGER.info("redirecting to home");
		return new ModelAndView("redirect:/");
	}

	public GlobalService getGlobalService() {
		return globalService;
	}

	public void setGlobalService(GlobalService globalService) {
		this.globalService = globalService;
	}
}
