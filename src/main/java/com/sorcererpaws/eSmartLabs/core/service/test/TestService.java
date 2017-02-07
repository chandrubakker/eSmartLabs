package com.sorcererpaws.eSmartLabs.core.service.test;

import java.util.List;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Test;

public interface TestService {

	public Test createTest(Test test);
	public Test updateTest(Test test);
	public Test getTest(long testId);
	public boolean deleteTest(long testId);
	public List<Test> testsByPatient(long patientId);
	public List<Test> testsByDepartment(long departmentId);
	public List<Test> testsByLab(long labId);
	public List<Test> allTests();
	public boolean isTestExists(String testCode);
}
