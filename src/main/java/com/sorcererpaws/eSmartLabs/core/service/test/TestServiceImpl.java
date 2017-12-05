package com.sorcererpaws.eSmartLabs.core.service.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sorcererpaws.eSmartLabs.core.dao.test.TestDao;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Test;
import com.sorcererpaws.eSmartLabs.core.entity.lab.TestGroup;

@Service
@Transactional(readOnly = true)
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDao testDao;

	@Override
	@Transactional(readOnly = false)
	public Test createTest(Test test) {
		return (test.getId() == 0 ? getTestDao().createTest(test) : null);
	}

	@Override
	@Transactional(readOnly = false)
	public Test updateTest(Test test) {
		return (test.getId() > 0 ? getTestDao().updateTest(test) : null);
	}

	@Override
	public Test getTest(long testId) {
		return (testId > 0 ? getTestDao().getTest(testId) : null);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteTest(long testId) {
		return (testId > 0 ? getTestDao().deleteTest(testId) : false);
	}

	@Override
	public List<Test> testsByPatient(long patientId) {
		return getTestDao().testsByPatient(patientId);
	}

	@Override
	public List<Test> testsByDepartment(long departmentId) {
		return getTestDao().testsByDepartment(departmentId);
	}

	@Override
	public List<Test> testsByLab(long labId) {
		
		return getTestDao().testsByLab(labId);
	}
	
	@Override
	public List<Test> allTests() {
		return getTestDao().allTests();
	}

	@Override
	public boolean isTestExists(String testCode) {
		
		return getTestDao().isTestExists(testCode);
	}

	@Override
	@Transactional(readOnly = false)
	public TestGroup createTestGroup(TestGroup testGroup) {
		
		return getTestDao().createTestGroup(testGroup);
	}

	@Override
	public TestGroup getTestGroup(Long testGroupId) {
		
		return getTestDao().getTestGroup(testGroupId);
	}

	@Override
	@Transactional(readOnly = false)
	public TestGroup updateTestGroup(TestGroup testGroup) {
		
		return getTestDao().updateTestGroup(testGroup);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteTestGroup(Long testGroupId) {
		
		return getTestDao().deleteTestGroup(testGroupId);
	}

	@Override
	public List<TestGroup> testGroups() {
		
		return getTestDao().testGroups();
	}

	@Override
	public List<Test> testsByTestGroup(Long testGroupId) {
		
		return getTestDao().testsByTestGroup(testGroupId);
	}

	@Override
	public List<TestGroup> testGroupsByLab(long labId) {
		
		return getTestDao().testGroupsByLab(labId);
	}

	//Getters and setters
	public TestDao getTestDao() {
		return testDao;
	}

	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}
}