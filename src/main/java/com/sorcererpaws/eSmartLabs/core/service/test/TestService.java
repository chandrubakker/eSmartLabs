package com.sorcererpaws.eSmartLabs.core.service.test;

import java.util.List;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Test;
import com.sorcererpaws.eSmartLabs.core.entity.lab.TestGroup;
import com.sorcererpaws.eSmartLabs.core.entity.respo.CustomTest;

public interface TestService {

	public Test createTest(Test test);
	public Test updateTest(Test test);
	public Test getTest(long testId);
	public boolean deleteTest(long testId);
	public List<Test> testsByPatient(long patientId);
	public List<Test> testsByDepartment(long departmentId);
	
	public List<CustomTest> customTestsByDepartment(long departmentId);
	
	public List<Test> testsByLab(long labId);
	public List<Test> allTests();
	public boolean isTestExists(String testCode);
	
	public TestGroup createTestGroup(TestGroup testGroup);
	public TestGroup getTestGroup(Long testGroupId);
	public TestGroup updateTestGroup(TestGroup testGroup);
	public boolean deleteTestGroup(Long testGroupId);
	public List<TestGroup> testGroups();
	public List<TestGroup> testGroupsByLab(long labId);
	public List<Test> testsByTestGroup(Long testGroupId);
}
