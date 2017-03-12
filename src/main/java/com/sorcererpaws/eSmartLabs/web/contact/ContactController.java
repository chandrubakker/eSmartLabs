package com.sorcererpaws.eSmartLabs.web.contact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sorcererpaws.eSmartLabs.core.entity.common.Contact;

@Controller
public class ContactController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);
	
	@RequestMapping(value = "/contact-us", method = RequestMethod.GET)
	public ModelAndView createContact() {
		
		LOGGER.info("getting contact-us page...");
		return new ModelAndView("home/contact/create").addObject("contact", new Contact());
	}
}
