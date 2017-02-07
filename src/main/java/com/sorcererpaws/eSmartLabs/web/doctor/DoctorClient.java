package com.sorcererpaws.eSmartLabs.web.doctor;

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

import com.sorcererpaws.eSmartLabs.core.entity.clinic.Doctor;
import com.sorcererpaws.eSmartLabs.core.entity.validation.ErrorMessage;
import com.sorcererpaws.eSmartLabs.core.entity.validation.ValidationResponse;
import com.sorcererpaws.eSmartLabs.core.service.doctor.DoctorService;
import com.sorcererpaws.eSmartLabs.core.validator.LabValidator;

@RestController
public class DoctorClient {

	@Autowired
	private DoctorService doctorService;
	@Autowired
	private LabValidator labValidator;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DoctorClient.class);
	
	@RequestMapping(value = "/doctors.json", method = RequestMethod.GET)
	public ResponseEntity<List<Doctor>> getAllDoctors() {
		
		LOGGER.info("Getting all doctors...");
		return new ResponseEntity<List<Doctor>>(getDoctorService().allDoctors(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/lab/{labId}/doctors.json", method = RequestMethod.GET)
	public ResponseEntity<List<Doctor>> getDoctorsByLab(@PathVariable("labId")long labId) {
		
		LOGGER.info("Getting all doctors by lab...");
		return new ResponseEntity<List<Doctor>>(getDoctorService().doctorsByLab(labId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/clinic/{clinicId}/doctors.json", method = RequestMethod.GET)
	public ResponseEntity<List<Doctor>> getDoctorsByClinic(@PathVariable("clinicId")long clinicId) {
		
		LOGGER.info("Getting all doctors by clinic...");
		return new ResponseEntity<List<Doctor>>(getDoctorService().doctorsByClinic(clinicId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/doctor/update", method = RequestMethod.POST)
	public ResponseEntity<ValidationResponse> createOrUpdateDoctor(@ModelAttribute("doctor")Doctor doctor, BindingResult bindingResult) {
		ValidationResponse response = new ValidationResponse();
		try {
			
			getLabValidator().validateUpdateDoctor(doctor, bindingResult);
			if(bindingResult.hasErrors()) {
				
				List<FieldError> allErrors = bindingResult.getFieldErrors();
				List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
				for (FieldError objectError : allErrors) {
					errorMesages.add(new ErrorMessage(objectError.getField(), objectError.getDefaultMessage()));
				}
				
				response.setErrorMessageList(errorMesages);
				response.setStatus("FAIL");
				LOGGER.info("Error while creating/updating doctor.");
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.BAD_REQUEST);
			}else {
				if(doctor.getId() == 0) {
				
					LOGGER.info("creating doctor...");
					doctor = getDoctorService().createDoctor(doctor);
					LOGGER.info(doctor.getName() + " doctor created.");
				} else {
					
					LOGGER.info("updating doctor...");
					doctor = getDoctorService().updateDoctor(doctor);
					LOGGER.info(doctor.getName() + " doctor updated.");
				}
				response.setObject(doctor);
				response.setStatus("SUCCESS");
				
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			LOGGER.info("Error while creating/updating doctor: "+e.getMessage());
			response.setStatus("FAIL");
			return new ResponseEntity<ValidationResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/doctor/{doctorId}", method = RequestMethod.GET)
	public ResponseEntity<Doctor> getDoctor(@PathVariable("doctorId")long doctorId) {
		Doctor doctor = getDoctorService().getDoctor(doctorId);
		if(doctor == null) {
			return new ResponseEntity<Doctor>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/doctor/{doctorId}/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Doctor> deleteDoctor(@PathVariable("doctorId")long doctorId) {
		Doctor doctor = getDoctorService().getDoctor(doctorId);
		if(doctor == null) {
			
			return new ResponseEntity<Doctor>(HttpStatus.NOT_FOUND);
		} else {
			if(getDoctorService().deleteDoctor(doctorId)) {
				
				return new ResponseEntity<Doctor>(HttpStatus.NO_CONTENT);
			} else {
				
				return new ResponseEntity<Doctor>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	//Getters and setters
	public DoctorService getDoctorService() {
		return doctorService;
	}
	public void setDoctorService(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	public LabValidator getLabValidator() {
		return labValidator;
	}

	public void setLabValidator(LabValidator labValidator) {
		this.labValidator = labValidator;
	}
}
