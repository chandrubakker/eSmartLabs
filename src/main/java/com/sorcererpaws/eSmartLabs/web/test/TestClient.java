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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Test;
import com.sorcererpaws.eSmartLabs.core.entity.lab.TestGroup;
import com.sorcererpaws.eSmartLabs.core.entity.respo.CustomTest;
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
	public ResponseEntity<List<CustomTest>> allTests() {
		
		LOGGER.info("getting all tests...");
		List<CustomTest> customTests = new ArrayList<CustomTest>();
		List<Test> tests = getTestService().allTests();
		
		for(Test test: tests) {
			
			CustomTest customTest = new CustomTest();
			
			customTest.setTestId(test.getId());
			customTest.setDeptId(test.getDepartment().getId());
			customTest.setLabId(test.getDepartment().getLab().getId());
			
			customTest.setTestName(test.getName());
			customTest.setTestCode(test.getCode());
			customTest.setDeptName(test.getDepartment().getName());
			customTest.setPrice(test.getPrice());
			customTest.setLabName(test.getDepartment().getLab().getName());
			
			customTests.add(customTest);
		}
		
		return new ResponseEntity<List<CustomTest>>(customTests, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/lab/{labId}/tests.json", method = RequestMethod.GET)
	public ResponseEntity<List<CustomTest>> testsByLab(@PathVariable("labId")long labId) {
		
		LOGGER.info("getting tests by lab...");
		List<CustomTest> customTests = new ArrayList<CustomTest>();
		List<Test> tests = getTestService().testsByLab(labId);
		
		for(Test test: tests) {
			
			CustomTest customTest = new CustomTest();
			
			customTest.setTestId(test.getId());
			customTest.setDeptId(test.getDepartment().getId());
			customTest.setLabId(test.getDepartment().getLab().getId());
			
			customTest.setTestName(test.getName());
			customTest.setTestCode(test.getCode());
			customTest.setDeptName(test.getDepartment().getName());
			customTest.setPrice(test.getPrice());
			customTest.setLabName(test.getDepartment().getLab().getName());
			
			customTests.add(customTest);
		}
		
		return new ResponseEntity<List<CustomTest>>(customTests, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/department/{deptId}/tests.json", method = RequestMethod.GET)
	public ResponseEntity<List<CustomTest>> testsByDepartment(@PathVariable("deptId")long deptId) {
		
		List<CustomTest> customTests = new ArrayList<CustomTest>();
		List<Test> tests = getTestService().testsByDepartment(deptId);
		
		for(Test test: tests) {
			
			CustomTest customTest = new CustomTest();
			
			customTest.setTestId(test.getId());
			customTest.setDeptId(test.getDepartment().getId());
			customTest.setLabId(test.getDepartment().getLab().getId());
			
			customTest.setTestName(test.getName());
			customTest.setTestCode(test.getCode());
			customTest.setDeptName(test.getDepartment().getName());
			customTest.setPrice(test.getPrice());
			customTest.setLabName(test.getDepartment().getLab().getName());
			
			customTests.add(customTest);
		}
		
		return new ResponseEntity<List<CustomTest>>(customTests, HttpStatus.OK);
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
	
	@RequestMapping(value = "/test-group/create", method = RequestMethod.POST)
	public ResponseEntity<ValidationResponse> createTestGroup(@ModelAttribute("testGroup")TestGroup testGroup, BindingResult bindingResult, @RequestParam(value = "testsToDo", required = false)String testsToDo[]) {
		ValidationResponse response = new ValidationResponse();
		try {
			
			List<Test> tests = null;
			Test test = null;
			if(testsToDo != null) {
				if(testsToDo.length > 0) {
					
					LOGGER.info(testsToDo.length + " - Tests selected");
					tests = new ArrayList<Test>();
					for(String id : testsToDo) {
						
						LOGGER.info("Test ID: " + id);
						test = getTestService().getTest(Long.parseLong(id));
						LOGGER.info("Test Name: " + test.getName());
						tests.add(test);
					}
					testGroup.setTests(tests);
				}
			}
			
			getLabValidator().validateCreateTestGroup(testGroup, bindingResult);
			if(bindingResult.hasErrors()) {
				
				List<FieldError> allErrors = bindingResult.getFieldErrors();
				List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
				for (FieldError objectError : allErrors) {
					errorMesages.add(new ErrorMessage(objectError.getField(), objectError.getDefaultMessage()));
				}
				
				response.setErrorMessageList(errorMesages);
				response.setStatus("FAIL");
				LOGGER.info("Error while creating/updating test group.");
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.BAD_REQUEST);
			}else {

				LOGGER.info("creating test group...");
				testGroup = getTestService().createTestGroup(testGroup);
				LOGGER.info(testGroup.getName() + " test group created.");
			
				response.setObject(testGroup);
				response.setStatus("SUCCESS");
				
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			LOGGER.info("Error while creating/updating test group: "+e.getMessage());
			response.setStatus("FAIL");
			return new ResponseEntity<ValidationResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/test-group/update", method = RequestMethod.POST)
	public ResponseEntity<ValidationResponse> updateTestGroup(@ModelAttribute("testGroup")TestGroup testGroup, BindingResult bindingResult, @RequestParam(value = "testsToDo", required = false)String testsToDo[]) {
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
					testGroup.setTests(tests);
				}
			}
			
			getLabValidator().validateCreateTestGroup(testGroup, bindingResult);
			if(bindingResult.hasErrors()) {
				
				List<FieldError> allErrors = bindingResult.getFieldErrors();
				List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
				for (FieldError objectError : allErrors) {
					errorMesages.add(new ErrorMessage(objectError.getField(), objectError.getDefaultMessage()));
				}
				
				response.setErrorMessageList(errorMesages);
				response.setStatus("FAIL");
				LOGGER.info("Error while creating/updating test group.");
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.BAD_REQUEST);
			}else {

				LOGGER.info("creating patient...");
				testGroup = getTestService().createTestGroup(testGroup);
				LOGGER.info(testGroup.getName() + " test group updated.");
			
				response.setObject(testGroup);
				response.setStatus("SUCCESS");
				
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			LOGGER.info("Error while creating/updating test group: "+e.getMessage());
			response.setStatus("FAIL");
			return new ResponseEntity<ValidationResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/test-groups.json", method = RequestMethod.GET)
	public ResponseEntity<List<TestGroup>> allTestGroups() {
		
		LOGGER.info("getting all test groups...");
		return new ResponseEntity<List<TestGroup>>(getTestService().testGroups(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/lab/{labId}/test-groups.json", method = RequestMethod.GET)
	public ResponseEntity<List<TestGroup>> testGroupsByLab(@PathVariable("labId")long labId) {
		
		LOGGER.info("getting test groups by lab...");
		return new ResponseEntity<List<TestGroup>>(getTestService().testGroupsByLab(labId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/test-group/{testGroupId}/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Test> deleteTestGroup(@PathVariable("testGroupId")long testGroupId) {
		TestGroup testGroup = getTestService().getTestGroup(testGroupId);
		if(testGroup == null) {
			
			return new ResponseEntity<Test>(HttpStatus.NOT_FOUND);
		}
		if(getTestService().deleteTestGroup(testGroupId)) {
			
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
