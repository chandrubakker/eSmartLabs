package com.sorcererpaws.eSmartLabs.web.clinic;

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

import com.sorcererpaws.eSmartLabs.core.entity.clinic.Clinic;
import com.sorcererpaws.eSmartLabs.core.entity.respo.CustomClinic;
import com.sorcererpaws.eSmartLabs.core.entity.validation.ErrorMessage;
import com.sorcererpaws.eSmartLabs.core.entity.validation.ValidationResponse;
import com.sorcererpaws.eSmartLabs.core.service.clinic.ClinicService;
import com.sorcererpaws.eSmartLabs.core.service.geo.GeoService;
import com.sorcererpaws.eSmartLabs.core.validator.LabValidator;

@RestController
public class ClinicClient {

	private final static Logger LOGGER = LoggerFactory.getLogger(ClinicClient.class);
	
	@Autowired
	private ClinicService clinicService;
	@Autowired
	private GeoService geoService;
	@Autowired
	private LabValidator labValidator;
	
	@RequestMapping(value = "/clinics.json", method = RequestMethod.GET)
	public ResponseEntity<List<CustomClinic>> allClinics() {
		
		LOGGER.info("getting all hospitals...");
		
		List<CustomClinic> customClinics = new ArrayList<CustomClinic>();
		List<Clinic> clinics = getClinicService().clinics();
		
		for(Clinic clinic: clinics) {
			
			CustomClinic customClinic = new CustomClinic();
			customClinic.setClinicId(clinic.getId());
			customClinic.setLabId(clinic.getLab().getId());
			customClinic.setClinicName(clinic.getName());
			customClinic.setLabName(clinic.getLab().getName());
			
			customClinic.setPhone(clinic.getAddress() == null ? "Not Found" : clinic.getAddress().getPhone());
			customClinic.setLocation(clinic.getAddress() == null ? "Not Found" : clinic.getAddress().getLocality());
			
			customClinics.add(customClinic);
		}
		
		return new ResponseEntity<List<CustomClinic>>(customClinics, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/lab/{labId}/clinics.json", method = RequestMethod.GET)
	public ResponseEntity<List<CustomClinic>> clinicsByLab(@PathVariable("labId")long labId) {
		
		LOGGER.info("getting clinics by lab...");
		List<CustomClinic> customClinics = new ArrayList<CustomClinic>();
		List<Clinic> clinics = getClinicService().clinicsByLab(labId);
		
		for(Clinic clinic: clinics) {
			
			CustomClinic customClinic = new CustomClinic();
			customClinic.setClinicId(clinic.getId());
			customClinic.setLabId(clinic.getLab().getId());
			customClinic.setClinicName(clinic.getName());
			customClinic.setLabName(clinic.getLab().getName());
			
			customClinic.setPhone(clinic.getAddress() == null ? "Not Found" : clinic.getAddress().getPhone());
			customClinic.setLocation(clinic.getAddress() == null ? "Not Found" : clinic.getAddress().getLocality());
			
			customClinics.add(customClinic);
		}
		
		return new ResponseEntity<List<CustomClinic>>(customClinics, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/clinic/{clinicId}", method = RequestMethod.GET)
	public ResponseEntity<Clinic> getClinic(@PathVariable("clinicId")long clinicId) {
		Clinic clinic = getClinicService().getClinic(clinicId);
		if(clinic == null) {
			
			return new ResponseEntity<Clinic>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Clinic>(clinic, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/clinic/update", method = RequestMethod.POST)
	public ResponseEntity<ValidationResponse> createOrUpdateDept(@ModelAttribute("clinic")Clinic clinic, BindingResult bindingResult) {
		ValidationResponse response = new ValidationResponse();
		try {
			
			getLabValidator().validateUpdateClinic(clinic, bindingResult);
			if(bindingResult.hasErrors()) {
				
				List<FieldError> allErrors = bindingResult.getFieldErrors();
				List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
				for (FieldError objectError : allErrors) {
					errorMesages.add(new ErrorMessage(objectError.getField(), objectError.getDefaultMessage()));
				}
				
				response.setErrorMessageList(errorMesages);
				response.setStatus("FAIL");
				LOGGER.info("Error while creating/updating clinic.");
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.BAD_REQUEST);
			}else {
				
				clinic.setAddress(getGeoService().updateAddress(clinic.getAddress()));
				if(clinic.getId() == 0) {
				
					LOGGER.info("creating clinic...");
					clinic = getClinicService().createClinic(clinic);
					LOGGER.info(clinic.getName() + " clinic created.");
				} else {
					
					LOGGER.info("updating clinic...");
					clinic = getClinicService().updateClinic(clinic);
					LOGGER.info(clinic.getName() + " clinic updated.");
				}
				response.setObject(clinic);
				response.setStatus("SUCCESS");
				
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			LOGGER.info("Error while creating/updating test: "+e.getMessage());
			response.setStatus("FAIL");
			return new ResponseEntity<ValidationResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/clinic/{clinicId}/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Clinic> deleteClinic(@PathVariable("clinicId")long clinicId) {
		Clinic clinic = getClinicService().getClinic(clinicId);
		if(clinic == null) {
			return new ResponseEntity<Clinic>(HttpStatus.NOT_FOUND);
		} else {
			if(getClinicService().deleteClinic(clinicId)) {
				
				if(clinic.getAddress() != null) {
					getGeoService().deleteAddress(clinic.getAddress().getId());
				}
				return new ResponseEntity<Clinic>(HttpStatus.NO_CONTENT);
			} else {
				
				return new ResponseEntity<Clinic>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
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
