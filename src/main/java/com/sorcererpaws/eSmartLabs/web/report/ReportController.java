package com.sorcererpaws.eSmartLabs.web.report;

import java.util.ArrayList;
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
import com.sorcererpaws.eSmartLabs.core.entity.lab.Report;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Test;
import com.sorcererpaws.eSmartLabs.core.entity.lab.TestResult;
import com.sorcererpaws.eSmartLabs.core.entity.user.User;
import com.sorcererpaws.eSmartLabs.core.service.patient.PatientService;
import com.sorcererpaws.eSmartLabs.core.service.report.ReportService;
import com.sorcererpaws.eSmartLabs.core.service.result.TestResultService;
import com.sorcererpaws.eSmartLabs.core.service.test.TestService;

@Controller
public class ReportController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);
	
	@Autowired
	private TestService testService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private TestResultService testResultService;
	@Autowired
	private ReportService reportService;
	
	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public ModelAndView reports(HttpSession httpSession) {
		
		if(httpSession == null) {
			
			return new ModelAndView("redirect:/login?sessionExp");
		}
		
		User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
		if(loggedInUser == null) {
			
			return new ModelAndView("redirect:/login?sessionExp");
		}
		
		if(loggedInUser.getRole().getName().equalsIgnoreCase("ROLE_TECHNICIAN")) {
			
			return new ModelAndView("report/displayByLab");
		} else {
		
			LOGGER.info("getting all report...");
			return new ModelAndView("report/display");
		}
	}
	
	@RequestMapping(value = "/patient/{patientId}/report/generate", method = RequestMethod.GET)
	public ModelAndView createReport(@PathVariable("patientId")long patientId) {
		
		Patient patient = getPatientService().getPatient(patientId);
		List<Test> testsByPatient = getTestService().testsByPatient(patient.getId());
		Report report = new Report();
		List<TestResult> testResults = new ArrayList<TestResult>();
		
		for(Test test: testsByPatient) {
			
			TestResult testResult = new TestResult();
			testResult.setPatient(patient);
			testResult.setTest(test);
			testResult.setReport(report);
			
			testResults.add(testResult);
		}
		
		report.setPatient(patient);
		report.setTestResults(testResults);
		
		patient.setReport(report);
		return new ModelAndView("report/create").addObject("patient", patient);
	}
	
	@RequestMapping(value = "/patient/{patientId}/report/view", method = RequestMethod.GET)
	public ModelAndView getReport(@PathVariable("patientId")long patientId) {
		
		Patient patient = getPatientService().getPatient(patientId);
		patient.getReport().setTestResults(getTestResultService().testResultsByReport(patient.getReport().getId()));
		
		return new ModelAndView("report/view").addObject("patient", patient);
	}
	
	@RequestMapping(value = "/patient/{patientId}/report/update", method = RequestMethod.GET)
	public ModelAndView updateReport(@PathVariable("patientId")long patientId) {
		
		Patient patient = getPatientService().getPatient(patientId);
		patient.getReport().setTestResults(getTestResultService().testResultsByReport(patient.getReport().getId()));
		
		return new ModelAndView("report/update").addObject("patient", patient);
	}
	
	//Getters and setters
	public TestService getTestService() {
		return testService;
	}
	public void setTestService(TestService testService) {
		this.testService = testService;
	}
	public PatientService getPatientService() {
		return patientService;
	}
	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}
	public TestResultService getTestResultService() {
		return testResultService;
	}
	public void setTestResultService(TestResultService testResultService) {
		this.testResultService = testResultService;
	}
	public ReportService getReportService() {
		return reportService;
	}
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}
}
