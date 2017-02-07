package com.sorcererpaws.eSmartLabs.core.service.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sorcererpaws.eSmartLabs.core.dao.test.TestDao;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Test;

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

	//Getters and setters
	public TestDao getTestDao() {
		return testDao;
	}

	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}
}
