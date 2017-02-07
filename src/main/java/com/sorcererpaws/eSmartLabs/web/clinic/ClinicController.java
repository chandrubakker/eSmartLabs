package com.sorcererpaws.eSmartLabs.web.clinic;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sorcererpaws.eSmartLabs.core.entity.clinic.Clinic;
import com.sorcererpaws.eSmartLabs.core.entity.common.Address;
import com.sorcererpaws.eSmartLabs.core.entity.user.User;
import com.sorcererpaws.eSmartLabs.core.service.clinic.ClinicService;
import com.sorcererpaws.eSmartLabs.core.service.geo.GeoService;
import com.sorcererpaws.eSmartLabs.core.validator.LabValidator;

@Controller
public class ClinicController {

private final static Logger LOGGER = LoggerFactory.getLogger(ClinicController.class);
	
	@Autowired
	private ClinicService clinicService;
	@Autowired
	private GeoService geoService;
	@Autowired
	private LabValidator labValidator;
	
	@RequestMapping(value = "/clinics", method = RequestMethod.GET)
	public ModelAndView getClinics(HttpSession httpSession) {
		if(httpSession == null) {
			
			return new ModelAndView("redirect:/login?sessionExp");
		}
		
		User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
		if(loggedInUser == null) {
			
			return new ModelAndView("redirect:/login?sessionExp");
		}
		
		if(loggedInUser.getRole().getName().equalsIgnoreCase("ROLE_ADMIN")) {
			
			LOGGER.info("getting all clinics...");
			return new ModelAndView("clinic/display");
		} else {
			
			LOGGER.info("getting clinics by lab: " + loggedInUser.getClient().getLab().getName());
			return new ModelAndView("clinic/displayByLab");
		}
	}
	
	@RequestMapping(value = "/clinic/create", method = RequestMethod.GET)
	public ModelAndView createClinic() {
		
		Clinic clinic = new Clinic();
		clinic.setAddress(new Address());
		return new ModelAndView("clinic/create").addObject("clinic", clinic);
	}
	
	@RequestMapping(value = "/clinic/{clinicId}/update", method = RequestMethod.GET)
	public ModelAndView updateClinic(@PathVariable("clinicId")long clinicId) {
		
		Clinic clinic = getClinicService().getClinic(clinicId);
		return new ModelAndView("clinic/update").addObject("clinic", clinic);
	}
	
	@RequestMapping(value = "/clinic/{clinicId}/view", method = RequestMethod.GET)
	public ModelAndView viewClinic(@PathVariable("clinicId")long clinicId) {
		
		Clinic clinic = getClinicService().getClinic(clinicId);
		return new ModelAndView("clinic/view").addObject("clinic", clinic);
	}
	
	//Getters and setters
	public ClinicService getClinicService() {
		return clinicService;
	}
	public void setClinicService(ClinicService clinicService) {
		this.clinicService = clinicService;
	}
	public GeoService getGeoService() {
		return geoService;
	}
	public void setGeoService(GeoService geoService) {
		this.geoService = geoService;
	}
	public LabValidator getLabValidator() {
		return labValidator;
	}
	public void setLabValidator(LabValidator labValidator) {
		this.labValidator = labValidator;
	}
}
