package com.sorcererpaws.eSmartLabs.web.doctor;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sorcererpaws.eSmartLabs.core.entity.clinic.Doctor;
import com.sorcererpaws.eSmartLabs.core.entity.user.User;
import com.sorcererpaws.eSmartLabs.core.service.clinic.ClinicService;
import com.sorcererpaws.eSmartLabs.core.service.doctor.DoctorService;

@Controller
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	@Autowired
	private ClinicService clinicService;

	private static final Logger LOGGER = LoggerFactory.getLogger(DoctorController.class);

	@RequestMapping(value = "/doctors", method = RequestMethod.GET)
	public ModelAndView getAllDoctors(HttpSession httpSession) {

		if(httpSession == null) {

			return new ModelAndView("redirect:/login?sessionExp");
		}

		User loggedInUser = (User) httpSession.getAttribute("loggedInUser");

		if(loggedInUser == null) {

			return new ModelAndView("redirect:/login?sessionExp");
		}

		if(loggedInUser.getRole().getName().equalsIgnoreCase("ROLE_TECHNICIAN")) {

			LOGGER.info("getting doctors by lab: " + loggedInUser.getClient().getLab().getName());
			return new ModelAndView("doctor/displayByLab");
		} else {

			LOGGER.info("getting all doctors page...");
			return new ModelAndView("doctor/display");
		}
	}

	@RequestMapping(value = "/doctor/create", method = RequestMethod.GET)
	public ModelAndView createDoctor(HttpSession httpSession) {

		if(httpSession == null) {

			return new ModelAndView("redirect:/login?sessionExp");
		}

		User loggedInUser = (User) httpSession.getAttribute("loggedInUser");

		if(loggedInUser == null) {

			return new ModelAndView("redirect:/login?sessionExp");
		}
		
		return new ModelAndView("doctor/create").addObject("doctor", new Doctor())
				.addObject("clinics", getClinicService()
						.clinicsByLab(loggedInUser.getClient().getLab().getId()));
	}

	@RequestMapping(value = "/doctor/{doctorId}/update", method = RequestMethod.GET)
	public ModelAndView updateDoctor(@PathVariable("doctorId")long doctorId, HttpSession httpSession) {

		if(httpSession == null) {

			return new ModelAndView("redirect:/login?sessionExp");
		}

		User loggedInUser = (User) httpSession.getAttribute("loggedInUser");

		if(loggedInUser == null) {

			return new ModelAndView("redirect:/login?sessionExp");
		}
		
		return new ModelAndView("doctor/update").addObject("doctor", getDoctorService().getDoctor(doctorId))
				.addObject("clinics", getClinicService()
						.clinicsByLab(loggedInUser.getClient().getLab().getId()));
	}

	@RequestMapping(value = "/doctor/{doctorId}/view", method = RequestMethod.GET)
	public ModelAndView viewDoctor(@PathVariable("doctorId")long doctorId) {

		LOGGER.info("getting doctor...");
		return new ModelAndView("doctor/view").addObject("doctor", getDoctorService().getDoctor(doctorId));
	}

	//Getters and setters
	public DoctorService getDoctorService() {
		return doctorService;
	}
	public void setDoctorService(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	public ClinicService getClinicService() {
		return clinicService;
	}

	public void setClinicService(ClinicService clinicService) {
		this.clinicService = clinicService;
	}
}
