package com.sorcererpaws.eSmartLabs.web.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sorcererpaws.eSmartLabs.core.entity.common.Address;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Company;
import com.sorcererpaws.eSmartLabs.core.service.company.CompanyService;
import com.sorcererpaws.eSmartLabs.core.service.user.UserService;

@Controller
public class CompanyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class); 
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/company/profile", method = RequestMethod.GET)
	public ModelAndView getCompany() {
		
		return new ModelAndView("company/view").addObject("company", getCompanyService().getCompany(new Long(1)));
	}
	
	@RequestMapping(value = "/company/update", method = RequestMethod.GET)
	public ModelAndView updateCompany() {
		
		LOGGER.info("getting company update page...");
		Company company = getCompanyService().getCompany(new Long(1));
		
		if(company.getAddress() == null) {
			
			LOGGER.info("company do not have address...");
			company.setAddress(new Address());
		}
		return new ModelAndView("company/update").addObject("company", company);
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
}
