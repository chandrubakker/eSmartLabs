package com.sorcererpaws.eSmartLabs.core.validator;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sorcererpaws.eSmartLabs.core.entity.clinic.Clinic;
import com.sorcererpaws.eSmartLabs.core.entity.clinic.Doctor;
import com.sorcererpaws.eSmartLabs.core.entity.clinic.Patient;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Department;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Lab;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Report;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Test;
import com.sorcererpaws.eSmartLabs.core.entity.lab.TestResult;
import com.sorcererpaws.eSmartLabs.core.service.department.DepartmentService;
import com.sorcererpaws.eSmartLabs.core.service.test.TestService;

@Component
public class LabValidator implements Validator {

	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private TestService testService;
	
	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub

	}
	/**
	 * Used to validate the create/update lab
	 * @param lab
	 * @param errors
	 */
	public void validateCreateLab(Lab lab, Errors errors) {
		if(lab.getName().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "noCode", "enter name.");
		}
		
		if(lab.getAddress().getAddress().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.address", "noCode", "enter lab address.");
		}
	}
	
	/**
	 * Used to validate create/update department
	 * @param department
	 * @param errors
	 */
	public void validateUpdateDept(Department department, Errors errors) {
		if(department.getName().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "noCode", "enter department name.");
		}
		
		if(department.getCode().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "noCode", "enter department code.");
		} else {
			if(department.getId() == 0) {
				if(getDepartmentService().isDeptExists(department.getCode())) {
					errors.rejectValue("code", "noCode", "department with entered code is already exists.");
				}		
			} else {
				Department eDepartment = getDepartmentService().getDepartment(department.getId());
				if(!eDepartment.getCode().equalsIgnoreCase(department.getCode())) {
					if(getDepartmentService().isDeptExists(department.getCode())) {
						errors.rejectValue("code", "noCode", "department with entered code is already exists.");
					}
				}
			}
		}
	}
	
	/**
	 * Used to validate create/update test
	 * @param test
	 * @param errors
	 */
	public void validateUpdateTest(Test test, Errors errors) {
		
		if(test.getDepartment().getId() == -1) {
			errors.rejectValue("department.id", "noCode", "select department.");
		}
		
		if(test.getName().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "noCode", "enter test name.");
		}
		
		if(test.getCode().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "noCode", "enter test name.");
		} else {
			if(test.getId() == 0) {
				if(getTestService().isTestExists(test.getCode())) {
					errors.rejectValue("code", "noCode", "test with entered code is already exists.");
				}
			} else {
				Test eTest = getTestService().getTest(test.getId());
				if(!eTest.getCode().equalsIgnoreCase(test.getCode())) {
					if(getTestService().isTestExists(test.getCode())) {
						errors.rejectValue("code", "noCode", "test with entered code is already exists.");
					}
				}
			}
		}
		
		if(test.getUnitType().isEmpty()) {
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "unitType", "noCode", "select unit type");
		} else if(test.getUnitType().equals("ranges")) {
			
			if(test.getNormalMin() > 0.0) {
				if(test.getNormalMax() < test.getNormalMin()) {
					errors.rejectValue("normalMax", "noCode", "max. normal value must be greater than min. normal value.");
				}
				
				if(test.getUnitUsed().isEmpty()) {
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "unitUsed", "noCode", "enter unit used for test");
				}
	 		}
		}
		
		if(test.getPrice() == 0.0) {
			errors.rejectValue("price", "noCode", "enter the cost of this test.");
		}
	}
	
	/**
	 * Used to validate create/update clinic
	 * @param clinic
	 * @param errors
	 */
	public void validateUpdateClinic(Clinic clinic, Errors errors) {
		if(clinic.getName().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "noCode", "enter clinic/hospital name");
		}
		
		if(clinic.getAddress().getCity().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.city", "noCode", "enter city name");
		}
	}
	
	/**
	 * Used to validate create/update doctor
	 * @param doctor
	 * @param errors
	 */
	public void validateUpdateDoctor(Doctor doctor, Errors errors) {
		if(doctor.getClinic().getId() == -1) {
			errors.rejectValue("clinic.id", "noCode", "select clinic/hospital.");
		}
		
		if(doctor.getName().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "noCode", "enter doctor name");
		}
		
		if(doctor.getSpecialization().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "specialization", "noCode", "enter doctor's specialization");
		}
	}
	
	/**
	 * Used to validate create/update patient
	 * @param patient
	 * @param errors
	 */
	public void validateCreatePatient(Patient patient, Errors errors) {
		
		if(patient.getName().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "noCode", "enter doctor name.");
		}
		
		if(!patient.isSelfInterest()) {
			if(patient.getReferredDoctor().getId() == -1) {
				errors.rejectValue("referredDoctor.id", "noCode", "select referred doctor.");
			}
		}
		
		if(patient.getTests() == null) {
			errors.rejectValue("tests", "noCode", "select tests to perform.");
		}else if(patient.getTests() != null) {
			if(patient.getTests().size() == 0) {
				errors.rejectValue("tests", "noCode", "select tests to perform.");
			}
		}
		
		if(patient.getPhone().isEmpty()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "noCode", "enter contact number.");
		}
	}
	
	public void validateReport(Report report, Errors errors) {
		
		List<TestResult> testResults = report.getTestResults();
		Iterator<TestResult> iterator = testResults.iterator();
		int count = 0;
		while (iterator.hasNext()) {
			TestResult testResult = (TestResult) iterator.next();
			
			if(testResult.getObservedValue().isEmpty()) {
				
				errors.rejectValue("report.testResults["+count+"].observedValue", "noCode", "enter/select observed value");
			}
			
			count++;
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
}
