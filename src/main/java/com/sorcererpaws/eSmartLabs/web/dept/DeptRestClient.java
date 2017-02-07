package com.sorcererpaws.eSmartLabs.web.dept;

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

import com.sorcererpaws.eSmartLabs.core.entity.lab.Department;
import com.sorcererpaws.eSmartLabs.core.entity.validation.ErrorMessage;
import com.sorcererpaws.eSmartLabs.core.entity.validation.ValidationResponse;
import com.sorcererpaws.eSmartLabs.core.service.department.DepartmentService;
import com.sorcererpaws.eSmartLabs.core.validator.LabValidator;

@RestController
public class DeptRestClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeptRestClient.class);
	
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private LabValidator labValidator;
	
	@RequestMapping(value = "/departments.json", method = RequestMethod.GET)
	public ResponseEntity<List<Department>> departments() {
		return new ResponseEntity<List<Department>>(getDepartmentService().departments(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/lab/{labId}/departments.json", method = RequestMethod.GET)
	public ResponseEntity<List<Department>> departmentsByLab(@PathVariable("labId")long labId) {
		
		return new ResponseEntity<List<Department>>(getDepartmentService().departmentsByLab(labId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/department/update", method = RequestMethod.POST)
	public ResponseEntity<ValidationResponse> createOrUpdateDept(@ModelAttribute("department")Department department, BindingResult bindingResult) {
		ValidationResponse response = new ValidationResponse();
		try {
			
			getLabValidator().validateUpdateDept(department, bindingResult);
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
				
				Department newDepartment = null;
				if(department.getId() == 0) {
				
					newDepartment = getDepartmentService().createDepartment(department);	
				} else {
					
					newDepartment = getDepartmentService().getDepartment(department.getId());
					newDepartment.setInfo(department.getInfo());
					newDepartment.setName(department.getName());
					newDepartment.setCode(department.getCode());
					getDepartmentService().updateDepartment(newDepartment);
				}
				response.setObject(department);
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
	
	@RequestMapping(value = "/department/{deptId}/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Department> deleteDept(@PathVariable("deptId")long deptId) {
		
		LOGGER.info("Deleting dept. ID: " + deptId);
		Department department = getDepartmentService().getDepartment(deptId);
		
		if(department == null) {
			
			LOGGER.info("Requested dept. not found");
			return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
		}
		if(getDepartmentService().deleteDepartment(deptId)) {
			
			LOGGER.info(department.getName() + " : Dept. deleted.");
			return new ResponseEntity<Department>(HttpStatus.NO_CONTENT);
		} else {
			
			LOGGER.info("Error while deleting dept: " + department.getName());
			return new ResponseEntity<Department>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Getters and setters
	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public LabValidator getLabValidator() {
		return labValidator;
	}

	public void setLabValidator(LabValidator labValidator) {
		this.labValidator = labValidator;
	}
}
