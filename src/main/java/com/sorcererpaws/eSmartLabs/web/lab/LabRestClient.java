package com.sorcererpaws.eSmartLabs.web.lab;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Lab;
import com.sorcererpaws.eSmartLabs.core.entity.respo.CustomLab;
import com.sorcererpaws.eSmartLabs.core.entity.validation.ErrorMessage;
import com.sorcererpaws.eSmartLabs.core.entity.validation.ValidationResponse;
import com.sorcererpaws.eSmartLabs.core.service.geo.GeoService;
import com.sorcererpaws.eSmartLabs.core.service.lab.LabService;
import com.sorcererpaws.eSmartLabs.core.validator.LabValidator;

@RestController
public class LabRestClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(LabRestClient.class);
	
	@Autowired
	private LabService labService;
	@Autowired
	private GeoService geoService;
	@Autowired
	private LabValidator labValidator;
	
	@RequestMapping(value = "/labs.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomLab>> allLabs() {
		
		LOGGER.info("getting all labs...");
		
		List<CustomLab> customLabs = new ArrayList<CustomLab>();
		List<Lab> labs = getLabService().labs();
		
		for(Lab lab: labs) {
			
			CustomLab customLab = new CustomLab();
			
			customLab.setLabId(lab.getId());
			customLab.setClientId(lab.getClient().getId());
			customLab.setUserId(lab.getClient().getUser().getId());
			customLab.setAdminName(lab.getClient().getUser().getName());
			customLab.setAdminEmail(lab.getClient().getUser().getEmail());
			customLab.setLabName(lab.getName());
			
			customLab.setPhone(lab.getAddress() == null ? "Not Found" : lab.getAddress().getPhone());
			customLab.setLocation(lab.getAddress() == null ? "Not Found" : lab.getAddress().getLocality());
			
			customLabs.add(customLab);
		}
		return new ResponseEntity<List<CustomLab>>(customLabs, HttpStatus.OK);
	}

	@RequestMapping(value = "/lab/{labId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Lab> getLab(@PathVariable("labId") long labId) {
		LOGGER.info("Getting lab of ID: "+labId);
		Lab lab = getLabService().getLab(labId);
		if(lab == null) {
			LOGGER.info("Lab not found");
			return new ResponseEntity<Lab>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Lab>(lab, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/lab/update", method = RequestMethod.POST)
	public ResponseEntity<ValidationResponse> updateLab(@ModelAttribute("lab")Lab lab, BindingResult bindingResult) {
		ValidationResponse response = new ValidationResponse();
		try {
			
			getLabValidator().validateCreateLab(lab, bindingResult);
			if(bindingResult.hasErrors()) {
				
				List<FieldError> allErrors = bindingResult.getFieldErrors();
				List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
				for (FieldError objectError : allErrors) {
					errorMesages.add(new ErrorMessage(objectError.getField(), objectError.getDefaultMessage()));
				}
				
				response.setErrorMessageList(errorMesages);
				response.setStatus("FAIL");
				LOGGER.info("Error while creating lab.");
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.BAD_REQUEST);
			}else {
				
				lab.setAddress(getGeoService().updateAddress(lab.getAddress()));
				lab = getLabService().updateLab(lab);
				response.setObject(lab);
				response.setStatus("SUCCESS");
				
				LOGGER.info("Lab updated.");
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			LOGGER.info("Error while updating lab: "+e.getMessage());
			response.setStatus("FAIL");
			return new ResponseEntity<ValidationResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/lab/{labId}", method = RequestMethod.DELETE)
	public ResponseEntity<Lab> deleteLab(@PathVariable("labId") long labId) {
		try {
			LOGGER.info("Deleting lab: "+labId);
			Lab lab = getLabService().getLab(labId);
			if(lab == null) {
				LOGGER.info("Lab with Id: "+labId+" not found.");
				return new ResponseEntity<Lab>(HttpStatus.NOT_FOUND);
			} else {
				if(getLabService().deleteLab(labId)) {
					LOGGER.info("Lab with Id: "+labId+" deleted.");
					if(lab.getAddress() != null) {
						getGeoService().deleteAddress(lab.getAddress().getId());
					}
					return new ResponseEntity<Lab>(HttpStatus.NO_CONTENT);
				} else {
					return new ResponseEntity<Lab>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			return new ResponseEntity<Lab>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Getters and setters
	public LabService getLabService() {
		return labService;
	}

	public void setLabService(LabService labService) {
		this.labService = labService;
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
