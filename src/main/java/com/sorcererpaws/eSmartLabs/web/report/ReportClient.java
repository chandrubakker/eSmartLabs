package com.sorcererpaws.eSmartLabs.web.report;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sorcererpaws.eSmartLabs.core.entity.clinic.Patient;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Report;
import com.sorcererpaws.eSmartLabs.core.entity.lab.TestResult;
import com.sorcererpaws.eSmartLabs.core.entity.validation.ErrorMessage;
import com.sorcererpaws.eSmartLabs.core.entity.validation.ValidationResponse;
import com.sorcererpaws.eSmartLabs.core.service.patient.PatientService;
import com.sorcererpaws.eSmartLabs.core.service.report.ReportService;
import com.sorcererpaws.eSmartLabs.core.service.result.TestResultService;
import com.sorcererpaws.eSmartLabs.core.service.test.TestService;
import com.sorcererpaws.eSmartLabs.core.validator.LabValidator;

@RestController
public class ReportClient {

	/*/patient/report/update*/
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportClient.class);
	@Autowired
	private PatientService patientService;
	@Autowired
	private TestService testService;
	@Autowired
	private TestResultService testResultService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private LabValidator labValidator;
	
	@RequestMapping(value = "/reports.json", method = RequestMethod.GET)
	public ResponseEntity<List<Report>> reports() {
		
		LOGGER.info("getting all reports...");
		return new ResponseEntity<List<Report>>(getReportService().allReports(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/patient/{patientId}/report", method = RequestMethod.GET)
	public ResponseEntity<Report> reportByPatient(@PathVariable("patientId")long patientId) {
		
		Report report = getReportService().reportByPatient(patientId);
		if(report == null) {
			
			LOGGER.info("report not found...");
			return new ResponseEntity<Report>(HttpStatus.NOT_FOUND);
		}
		LOGGER.info("getting patient report...");
		return new ResponseEntity<Report>(report, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/report/{reportId}", method = RequestMethod.GET)
	public ResponseEntity<Report> getReport(@PathVariable("reportId")long reportId) {
		
		Report report = getReportService().getReport(reportId);
		if(report == null) {
			
			LOGGER.info("report not found...");
			return new ResponseEntity<Report>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Report>(report, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/patient/report/update", method = RequestMethod.POST)
	public ResponseEntity<ValidationResponse> createPatient(@ModelAttribute("patient")Patient patient, BindingResult bindingResult) {
		ValidationResponse response = new ValidationResponse();
		try {
			
			Report report = patient.getReport();
			List<TestResult> testResults = report.getTestResults();
			for(TestResult testResult: testResults) {
				
				testResult.setReport(report);
			}
			
			getLabValidator().validateReport(report, bindingResult);
			if(bindingResult.hasErrors()) {
				
				List<FieldError> allErrors = bindingResult.getFieldErrors();
				List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
				for (FieldError objectError : allErrors) {
					errorMesages.add(new ErrorMessage(objectError.getField(), objectError.getDefaultMessage()));
				}
				
				response.setErrorMessageList(errorMesages);
				response.setStatus("FAIL");
				LOGGER.info("Error while creating/updating report...");
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.BAD_REQUEST);
			}else {
				
				LOGGER.info("creation report...");
				report.setPatient(patient);
				report = getReportService().updateReport(report);
				LOGGER.info("report created.");
				response.setObject(report);
				response.setStatus("SUCCESS");
				
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			LOGGER.info("Error while creating/updating report: "+e.getMessage());
			response.setStatus("FAIL");
			return new ResponseEntity<ValidationResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Getters and setters
	public PatientService getPatientService() {
		return patientService;
	}
	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}
	public TestService getTestService() {
		return testService;
	}
	public void setTestService(TestService testService) {
		this.testService = testService;
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

	public LabValidator getLabValidator() {
		return labValidator;
	}

	public void setLabValidator(LabValidator labValidator) {
		this.labValidator = labValidator;
	}
}
