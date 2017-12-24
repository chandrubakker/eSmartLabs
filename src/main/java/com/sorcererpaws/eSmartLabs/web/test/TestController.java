package com.sorcererpaws.eSmartLabs.web.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Test;
import com.sorcererpaws.eSmartLabs.core.entity.lab.TestGroup;
import com.sorcererpaws.eSmartLabs.core.entity.user.User;
import com.sorcererpaws.eSmartLabs.core.service.department.DepartmentService;
import com.sorcererpaws.eSmartLabs.core.service.test.TestService;

@Controller
public class TestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private TestService testService;
	
	private Map<String, String> unitTypes() {
		
		Map<String, String> types = new HashMap<String, String>();
		types.put("ranges", "Has Ranges");
		types.put("neg_pos", "Negative/Positive");
		
		return types;
	}

	@RequestMapping(value = "/tests", method = RequestMethod.GET)
	public String allTests(HttpSession httpSession) {
		
		if(httpSession == null) {
			
			return "redirect:/login?sessionExp";
		}
		
		User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
		if(loggedInUser == null) {
			
			return "redirect:/login?sessionExp";
		}
		
		if(loggedInUser.getRole().getName().equalsIgnoreCase("ROLE_TECHNICIAN")) {
			
			LOGGER.info("getting tests by lab: " + loggedInUser.getClient().getLab().getName());
			return "test/displayByLab";
		} else {
			
			LOGGER.info("getting all tests...");
			return "test/display";
		}
	}
	
	@RequestMapping(value = "/test/create", method = RequestMethod.GET)
	public ModelAndView createTest(HttpSession httpSession) {
		
		if(httpSession != null) {
			User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
			if(loggedInUser != null) {
				return new ModelAndView("test/create").addObject("test", new Test())
						.addObject("departments", getDepartmentService()
								.departmentsByLab(loggedInUser.getClient().getLab().getId()))
						.addObject("unitTypes", unitTypes());
			}
		}
		
		return new ModelAndView("redirect:/login?sessionExp");
	}
	
	@RequestMapping(value = "/test/{testId}/update", method = RequestMethod.GET)
	public ModelAndView updateTest(@PathVariable("testId")long testId, HttpSession httpSession) {
		
		if(httpSession != null) {
			User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
			if(loggedInUser != null) {
				return new ModelAndView("test/update").addObject("test", getTestService().getTest(testId))
						.addObject("departments", getDepartmentService()
								.departmentsByLab(loggedInUser.getClient().getLab().getId()))
						.addObject("unitTypes", unitTypes());
			}
		}
		
		return new ModelAndView("redirect:/login?sessionExp");
	}
	
	@RequestMapping(value = "/test/{testId}/view", method = RequestMethod.GET)
	public ModelAndView viewTest(@PathVariable("testId")long testId) {
		return new ModelAndView("test/view").addObject("test", getTestService().getTest(testId));
	}
	
	
	@RequestMapping(value = "/test-groups", method = RequestMethod.GET)
	public String allTestGroups(HttpSession httpSession) {
		
		if(httpSession == null) {
			
			return "redirect:/login?sessionExp";
		}
		
		User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
		if(loggedInUser == null) {
			
			return "redirect:/login?sessionExp";
		}
		
		if(loggedInUser.getRole().getName().equalsIgnoreCase("ROLE_TECHNICIAN")) {
			
			LOGGER.info("getting test-groups by lab: " + loggedInUser.getClient().getLab().getName());
			return "test-group/displayByLab";
		} else {
			
			LOGGER.info("getting all test-groups...");
			return "test-group/display";
		}
	}
	
	@RequestMapping(value = "/test-group/create", method = RequestMethod.GET)
	public ModelAndView createTestGroup(HttpSession httpSession) {
		
		if(httpSession == null) {

			return new ModelAndView("redirect:/login?sessionExp");
		}

		User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
		if(loggedInUser == null) {

			return new ModelAndView("redirect:/login?sessionExp");
		}
		
		LOGGER.info("getting test group create page...");
		
		return new ModelAndView("test-group/create")
				.addObject("testGroup", new TestGroup())
				.addObject("tests", getTestService().testsByLab(loggedInUser.getClient().getLab().getId()));
	}
	
	@RequestMapping(value = "/test-group/update", method = RequestMethod.GET)
	public ModelAndView updateTestGroup(@PathVariable("testGroupId")long testGroupId, HttpSession httpSession) {
		
		if(httpSession == null) {

			return new ModelAndView("redirect:/login?sessionExp");
		}

		User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
		if(loggedInUser == null) {

			return new ModelAndView("redirect:/login?sessionExp");
		}
		
		LOGGER.info("getting test group update page...");
		
		TestGroup testGroup = getTestService().getTestGroup(testGroupId);
		List<Test> selectedTests = getTestService().testsByTestGroup(testGroupId);
		List<Test> allTests = getTestService().testsByLab(loggedInUser.getClient().getLab().getId());
		
		for(Test mainTest: allTests) {

			for(Test selTest: selectedTests) {

				if(mainTest.getId() == selTest.getId()) {
					mainTest.setSelect("selected");
				}
			}
		}
		
		return new ModelAndView("test-group/update")
				.addObject("testGroup", testGroup)
				.addObject("tests", getTestService().testsByLab(loggedInUser.getClient().getLab().getId()));
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
}
