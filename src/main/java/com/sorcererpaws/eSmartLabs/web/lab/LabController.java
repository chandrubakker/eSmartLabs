package com.sorcererpaws.eSmartLabs.web.lab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sorcererpaws.eSmartLabs.core.entity.common.Address;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Lab;
import com.sorcererpaws.eSmartLabs.core.service.lab.LabService;

@Controller
public class LabController {

	@Autowired
	private LabService labService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LabController.class);
	
	@RequestMapping(value = "/labs", method = RequestMethod.GET)
	public ModelAndView allLabs() {
		
		return new ModelAndView("lab/display");
	}
	
	@RequestMapping(value = "/lab/{labId}/update", method = RequestMethod.GET)
	public ModelAndView updateLab(@PathVariable("labId")long labId) {
		
		Lab lab = getLabService().getLab(labId);
		LOGGER.info("Getting lab update page.");
		lab.setAddress(lab.getAddress() == null ? new Address() : lab.getAddress());
		return new ModelAndView("lab/update").addObject("lab", lab);
	}
	
	@RequestMapping(value = "/lab/{labId}/profile", method = RequestMethod.GET)
	public ModelAndView getLab(@PathVariable("labId")long labId) {
		
		return new ModelAndView("lab/view").addObject("lab", getLabService().getLab(labId));
	}
	
	//Getters and setters
	public LabService getLabService() {
		return labService;
	}

	public void setLabService(LabService labService) {
		this.labService = labService;
	}
}
