package com.sorcererpaws.eSmartLabs.web.patient;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sorcererpaws.eSmartLabs.core.entity.clinic.Patient;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Test;
import com.sorcererpaws.eSmartLabs.core.entity.user.User;
import com.sorcererpaws.eSmartLabs.core.service.doctor.DoctorService;
import com.sorcererpaws.eSmartLabs.core.service.patient.PatientService;
import com.sorcererpaws.eSmartLabs.core.service.test.TestService;

@Controller
public class PatientController {

	@Autowired
	private PatientService patientService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private TestService testService;

	private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);

	@RequestMapping(value = "/patients", method = RequestMethod.GET)
	public ModelAndView getAllPatientsPublic(HttpSession httpSession) {

		if(httpSession == null) {

			return new ModelAndView("redirect:/login?sessionExp");
		}

		User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
		if(loggedInUser == null) {

			return new ModelAndView("redirect:/login?sessionExp");
		}
		
		if(loggedInUser.getRole().getName().equalsIgnoreCase("ROLE_TECHNICIAN")) {
			
			LOGGER.info("getting patients by lab: " + loggedInUser.getClient().getLab().getName());
			return new ModelAndView("patient/displayByLab");
		} else {
			
			LOGGER.info("getting all patients...");
			return new ModelAndView("patient/display");
		}
	}

	@RequestMapping(value = "/patient/{patientId}/view", method = RequestMethod.GET)
	public ModelAndView getPatient(@PathVariable("patientId")long patientId) {

		Patient patient = getPatientService().getPatient(patientId);
		patient.setTests(getTestService().testsByPatient(patientId));

		LOGGER.info("getting public patients page");
		return new ModelAndView("patient/view").addObject("patient", patient);
	}

	@RequestMapping(value = "/patient/create", method = RequestMethod.GET)
	public ModelAndView createPatient(HttpSession httpSession) {

		if(httpSession == null) {

			return new ModelAndView("redirect:/login?sessionExp");
		}

		User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
		if(loggedInUser == null) {

			return new ModelAndView("redirect:/login?sessionExp");
		}

		LOGGER.info("getting public patient create page...");
		return new ModelAndView("patient/create").addObject("patient", new Patient())
				.addObject("doctors", getDoctorService()
						.doctorsByLab(loggedInUser.getClient().getLab().getId()))
				.addObject("tests", getTestService()
						.testsByLab(loggedInUser.getClient().getLab().getId()));
	}

	@RequestMapping(value = "/patient/{patientId}/update", method = RequestMethod.GET)
	public ModelAndView updatePatient(@PathVariable("patientId")long patientId, HttpSession httpSession) {

		if(httpSession == null) {

			return new ModelAndView("redirect:/login?sessionExp");
		}

		User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
		if(loggedInUser == null) {

			return new ModelAndView("redirect:/login?sessionExp");
		}
		
		Patient patient = getPatientService().getPatient(patientId);
		List<Test> selectedTests = getTestService().testsByPatient(patientId);
		List<Test> allTests = getTestService().testsByLab(loggedInUser.getClient().getLab().getId());

		for(Test mainTest: allTests) {

			for(Test selTest: selectedTests) {

				if(mainTest.getId() == selTest.getId()) {
					mainTest.setSelect("selected");
				}
			}
		}

		LOGGER.info("getting patient update page...");
		return new ModelAndView("patient/update").addObject("patient", patient)
				.addObject("doctors", getDoctorService().doctorsByLab(loggedInUser.getClient().getLab().getId()))
				.addObject("tests", allTests);
	}

	//Getters and setters
	public PatientService getPatientService() {
		return patientService;
	}

	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}

	public DoctorService getDoctorService() {
		return doctorService;
	}

	public void setDoctorService(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	public TestService getTestService() {
		return testService;
	}

	public void setTestService(TestService testService) {
		this.testService = testService;
	}
}
