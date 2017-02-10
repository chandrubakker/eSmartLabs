package com.sorcererpaws.eSmartLabs.web.patient;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sorcererpaws.eSmartLabs.core.entity.clinic.Patient;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Report;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Test;
import com.sorcererpaws.eSmartLabs.core.entity.respo.CustomPatient;
import com.sorcererpaws.eSmartLabs.core.entity.validation.ErrorMessage;
import com.sorcererpaws.eSmartLabs.core.entity.validation.ValidationResponse;
import com.sorcererpaws.eSmartLabs.core.service.doctor.DoctorService;
import com.sorcererpaws.eSmartLabs.core.service.patient.PatientService;
import com.sorcererpaws.eSmartLabs.core.service.report.ReportService;
import com.sorcererpaws.eSmartLabs.core.service.test.TestService;
import com.sorcererpaws.eSmartLabs.core.validator.LabValidator;

@RestController
public class PatientClient {

	@Autowired
	private PatientService patientService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private TestService testService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private LabValidator labValidator;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PatientClient.class);
	
	@RequestMapping(value = "/patients.json", method = RequestMethod.GET)
	public ResponseEntity<List<CustomPatient>> patients() {
		
		LOGGER.info("getting patients...");
		List<Patient> patients = getPatientService().allPatients();
		List<CustomPatient> customPatients = new ArrayList<CustomPatient>();
		
		for(Patient patient: patients) {
			
			CustomPatient customPatient = new CustomPatient();
			customPatient.setPatientId(patient.getId());
			customPatient.setReportFound(patient.getReport() == null ? false : true);
			customPatient.setPhone(patient.getPhone());
			customPatient.setPatientName(patient.getName());
			customPatient.setRefBy(patient.getReferredDoctor() == null ? "Self" : patient.getReferredDoctor().getName());
			customPatient.setRegNum(patient.getRegNum());
			customPatient.setClinicName(patient.getReferredDoctor() == null ? "Self" : patient.getReferredDoctor().getClinic().getName());
			customPatient.setLabName("");
			
			customPatients.add(customPatient);
		}
		
		return new ResponseEntity<List<CustomPatient>>(customPatients, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/doctor/{doctorId}/patients.json", method = RequestMethod.GET)
	public ResponseEntity<List<CustomPatient>> patientsByDoctor(@PathVariable("doctorId")long doctorId) {
		
		LOGGER.info("getting patients by doctor...");
		
		List<Patient> patients = getPatientService().patientsByDoctor(doctorId);
		List<CustomPatient> customPatients = new ArrayList<CustomPatient>();
		
		for(Patient patient: patients) {
			
			CustomPatient customPatient = new CustomPatient();
			customPatient.setPatientId(patient.getId());
			customPatient.setReportFound(patient.getReport() == null ? false : true);
			customPatient.setPhone(patient.getPhone());
			customPatient.setPatientName(patient.getName());
			customPatient.setRefBy(patient.getReferredDoctor() == null ? "Self" : patient.getReferredDoctor().getName());
			customPatient.setRegNum(patient.getRegNum());
			customPatient.setClinicName(patient.getReferredDoctor() == null ? "Self" : patient.getReferredDoctor().getClinic().getName());
			customPatient.setLabName("");
			
			customPatients.add(customPatient);
		}
		return new ResponseEntity<List<CustomPatient>>(customPatients, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/lab/{labId}/patients.json", method = RequestMethod.GET)
	public ResponseEntity<List<CustomPatient>> patientsByLab(@PathVariable("labId")long labId) {
		
		LOGGER.info("getting patients by lab...");
		
		List<Patient> patients = getPatientService().patientsByLab(labId);
		List<CustomPatient> customPatients = new ArrayList<CustomPatient>();
		
		for(Patient patient: patients) {
			
			CustomPatient customPatient = new CustomPatient();
			customPatient.setPatientId(patient.getId());
			customPatient.setReportFound(patient.getReport() == null ? false : true);
			customPatient.setPhone(patient.getPhone());
			customPatient.setPatientName(patient.getName());
			customPatient.setRefBy(patient.getReferredDoctor() == null ? "Self" : patient.getReferredDoctor().getName());
			customPatient.setRegNum(patient.getRegNum());
			customPatient.setClinicName(patient.getReferredDoctor() == null ? "Self" : patient.getReferredDoctor().getClinic().getName());
			customPatient.setLabName("");
			
			customPatients.add(customPatient);
		}
		return new ResponseEntity<List<CustomPatient>>(customPatients, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/patient/{patientId}", method = RequestMethod.GET)
	public ResponseEntity<Patient> getPatient(@PathVariable("patientId")long patientId) {
		
		Patient patient = getPatientService().getPatient(patientId);
		if(patient == null) {
			return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Patient>(patient, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/patient/{patientId}/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Patient> deletePatient(@PathVariable("patientId")long patientId) {
		
		Patient patient = getPatientService().getPatient(patientId);
		if(patient == null) {
			
			return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
		}
		if(getPatientService().deletePatient(patientId)) {
			
			return new ResponseEntity<Patient>(HttpStatus.NO_CONTENT);
		} else {
			
			return new ResponseEntity<Patient>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/patient/create", method = RequestMethod.POST)
	public ResponseEntity<ValidationResponse> createPatient(@ModelAttribute("patient")Patient patient, BindingResult bindingResult, @RequestParam(value = "testsToDo", required = false)String testsToDo[]) {
		ValidationResponse response = new ValidationResponse();
		try {
			
			List<Test> tests = null;
			Test test = null;
			if(testsToDo != null) {
				if(testsToDo.length > 0) {
					
					LOGGER.info(testsToDo.length + "Tests selected");
					tests = new ArrayList<Test>();
					for(String id : testsToDo) {
						
						LOGGER.info("Test ID: " + id);
						test = getTestService().getTest(Long.parseLong(id));
						LOGGER.info("Test Name: " + test.getName());
						tests.add(test);
					}
					patient.setTests(tests);
				}
			}
			
			getLabValidator().validateCreatePatient(patient, bindingResult);
			if(bindingResult.hasErrors()) {
				
				List<FieldError> allErrors = bindingResult.getFieldErrors();
				List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
				for (FieldError objectError : allErrors) {
					errorMesages.add(new ErrorMessage(objectError.getField(), objectError.getDefaultMessage()));
				}
				
				response.setErrorMessageList(errorMesages);
				response.setStatus("FAIL");
				LOGGER.info("Error while creating/updating patient.");
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.BAD_REQUEST);
			}else {
				

				patient.setDateOfAdmission(new Date());
				if(patient.isSelfInterest()) {
					
					LOGGER.info("creating self interested patient...");
					patient.setReferredDoctor(null);
				}
				
				LOGGER.info("creating patient...");
				patient = getPatientService().createPatient(patient);
				LOGGER.info(patient.getName() + " patient created.");
			
				response.setObject(patient);
				response.setStatus("SUCCESS");
				
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			LOGGER.info("Error while creating/updating patient: "+e.getMessage());
			response.setStatus("FAIL");
			return new ResponseEntity<ValidationResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/patient/update", method = RequestMethod.POST)
	public ResponseEntity<ValidationResponse> updatePatient(@ModelAttribute("patient")Patient patient, BindingResult bindingResult, @RequestParam(value = "testsToDo", required = false)String testsToDo[]) {
		ValidationResponse response = new ValidationResponse();
		try {
			
			List<Test> tests = null;
			Test test = null;
			if(testsToDo != null) {
				if(testsToDo.length > 0) {
					
					LOGGER.info(testsToDo.length + "Tests selected");
					tests = new ArrayList<Test>();
					for(String id : testsToDo) {
						
						LOGGER.info("Test ID: " + id);
						test = getTestService().getTest(Long.parseLong(id));
						LOGGER.info("Test Name: " + test.getName());
						tests.add(test);
					}
					patient.setTests(tests);
				}
			}
			
			getLabValidator().validateCreatePatient(patient, bindingResult);
			if(bindingResult.hasErrors()) {
				
				List<FieldError> allErrors = bindingResult.getFieldErrors();
				List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
				for (FieldError objectError : allErrors) {
					errorMesages.add(new ErrorMessage(objectError.getField(), objectError.getDefaultMessage()));
				}
				
				response.setErrorMessageList(errorMesages);
				response.setStatus("FAIL");
				LOGGER.info("Error while creating/updating patient.");
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.BAD_REQUEST);
			}else {
				
				

				if(patient.isSelfInterest()) {
					
					LOGGER.info("creating self interested patient...");
					patient.setReferredDoctor(null);
				}
				
				LOGGER.info("updating patient...");
				patient = getPatientService().updatePatient(patient);
				
				Report report = getReportService().reportByPatient(patient.getId());
				if(report != null) {
					
					LOGGER.info("deleting old report...");
					getReportService().deleteReport(report.getId());
					LOGGER.info("report deleted....");
				}
				
				LOGGER.info(patient.getName() + " patient updated.");
			
				response.setObject(patient);
				response.setStatus("SUCCESS");
				
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			LOGGER.info("Error while creating/updating patient: "+e.getMessage());
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
