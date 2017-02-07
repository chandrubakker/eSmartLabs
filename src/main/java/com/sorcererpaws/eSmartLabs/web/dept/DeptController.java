package com.sorcererpaws.eSmartLabs.web.dept;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Department;
import com.sorcererpaws.eSmartLabs.core.entity.user.User;
import com.sorcererpaws.eSmartLabs.core.service.department.DepartmentService;

@Controller
public class DeptController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeptController.class);
	
	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping(value = "/departments", method = RequestMethod.GET)
	public ModelAndView departments(HttpSession httpSession) {
		
		if(httpSession != null) {
			User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
			if(loggedInUser != null) {
				
				if(loggedInUser.getRole().getName().equalsIgnoreCase("ROLE_TECHNICIAN")) {
				
					LOGGER.info("getting departments by lab..."+ loggedInUser.getClient().getLab().getName());
					return new ModelAndView("dept/displayByLab");
				} else {
					
					LOGGER.info("getting all departments...");
					return new ModelAndView("dept/display");
				}
			} else {
				
				LOGGER.info("session expired while getting departments...");
				return new ModelAndView("redirect:/login?sessionExp");
			}
		} else {
			
			LOGGER.info("session expired while getting departments...");
			return new ModelAndView("redirect:/login?sessionExp");
		}
	}
	
	@RequestMapping(value = "/department/{deptId}/view", method = RequestMethod.GET)
	public ModelAndView department(@PathVariable("deptId")long deptId) {
		Department department = getDepartmentService().getDepartment(deptId);
		return new ModelAndView("dept/view").addObject("department", department);
	}
	
	@RequestMapping(value = "/department/create", method = RequestMethod.GET)
	public ModelAndView createDepartment() {
		return new ModelAndView("dept/create").addObject("department", new Department());
	}
	
	@RequestMapping(value = "/department/{deptId}/update", method = RequestMethod.GET)
	public ModelAndView updateDepartment(@PathVariable("deptId")long deptId) {
		return new ModelAndView("dept/update").addObject("department", getDepartmentService()
				.getDepartment(deptId));
	}

	//Getters and setters
	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
}
