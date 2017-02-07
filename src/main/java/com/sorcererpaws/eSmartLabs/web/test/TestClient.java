package com.sorcererpaws.eSmartLabs.web.test;

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
import org.springframework.web.bind.annotation.RestController;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Test;
import com.sorcererpaws.eSmartLabs.core.entity.validation.ErrorMessage;
import com.sorcererpaws.eSmartLabs.core.entity.validation.ValidationResponse;
import com.sorcererpaws.eSmartLabs.core.service.department.DepartmentService;
import com.sorcererpaws.eSmartLabs.core.service.test.TestService;
import com.sorcererpaws.eSmartLabs.core.validator.LabValidator;

@RestController
public class TestClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestClient.class);
	
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private TestService testService;
	@Autowired
	private LabValidator labValidator;
	
	@RequestMapping(value = "/tests.json", method = RequestMethod.GET)
	public ResponseEntity<List<Test>> allTests() {
		
		return new ResponseEntity<List<Test>>(getTestService().allTests(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/lab/{labId}/tests.json", method = RequestMethod.GET)
	public ResponseEntity<List<Test>> testsByLab(@PathVariable("labId")long labId) {
		
		return new ResponseEntity<List<Test>>(getTestService().testsByLab(labId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/department/{deptId}/tests.json", method = RequestMethod.GET)
	public ResponseEntity<List<Test>> testsByDepartment(@PathVariable("deptId")long deptId) {
		
		return new ResponseEntity<List<Test>>(getTestService().testsByDepartment(deptId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/test/update", method = RequestMethod.POST)
	public ResponseEntity<ValidationResponse> createOrUpdateDept(@ModelAttribute("test")Test test, BindingResult bindingResult) {
		ValidationResponse response = new ValidationResponse();
		try {
			
			getLabValidator().validateUpdateTest(test, bindingResult);
			if(bindingResult.hasErrors()) {
				
				List<FieldError> allErrors = bindingResult.getFieldErrors();
				List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
				for (FieldError objectError : allErrors) {
					errorMesages.add(new ErrorMessage(objectError.getField(), objectError.getDefaultMessage()));
				}
				
				response.setErrorMessageList(errorMesages);
				response.setStatus("FAIL");
				LOGGER.info("Error while creating/updating test.");
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.BAD_REQUEST);
			}else {
				
				test.setLastUpdatedDate(new Date());
				if(test.getId() == 0) {
				
					LOGGER.info("creating test...");
					test = getTestService().createTest(test);
					LOGGER.info(test.getName() + " test created.");
				} else {
					
					LOGGER.info("updating test...");
					test = getTestService().updateTest(test);
					LOGGER.info(test.getName() + " test updated.");
				}
				response.setObject(test);
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
	
	@RequestMapping(value = "/test/{testId}/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Test> deleteTest(@PathVariable("testId")long testId) {
		Test test = getTestService().getTest(testId);
		if(test == null) {
			
			return new ResponseEntity<Test>(HttpStatus.NOT_FOUND);
		}
		if(getTestService().deleteTest(testId)) {
			
			return new ResponseEntity<Test>(HttpStatus.NO_CONTENT);
		} else {
			
			return new ResponseEntity<Test>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Getters and setters
	public DepartmentService getDepartmentService() {
		return departmentService;
	}
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	public TestService getTestService() {
		return testService;
	}
	public void setTestService(TestService testService) {
		this.testService = testService;
	}

	public LabValidator getLabValidator() {
		return labValidator;
	}

	public void setLabValidator(LabValidator labValidator) {
		this.labValidator = labValidator;
	}
}
