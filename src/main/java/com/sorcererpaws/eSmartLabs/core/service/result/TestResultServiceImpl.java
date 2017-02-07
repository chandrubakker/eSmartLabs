package com.sorcererpaws.eSmartLabs.core.service.result;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sorcererpaws.eSmartLabs.core.dao.result.TestResultDao;
import com.sorcererpaws.eSmartLabs.core.entity.lab.TestResult;

@Service
@Transactional(readOnly = true)
public class TestResultServiceImpl implements TestResultService {

	@Autowired
	private TestResultDao testResultDao;

	@Override
	@Transactional(readOnly = false)
	public TestResult createTestResult(TestResult testResult) {
		return getTestResultDao().createTestResult(testResult);
	}

	@Override
	@Transactional(readOnly = false)
	public TestResult updateTestResult(TestResult testResult) {
		return getTestResultDao().updateTestResult(testResult);
	}

	@Override
	public TestResult getTestResult(long testResultId) {
		return getTestResultDao().getTestResult(testResultId);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteTestResult(long testResultId) {
		return getTestResultDao().deleteTestResult(testResultId);
	}

	@Override
	public List<TestResult> testResultsByReport(long reportId) {
		return getTestResultDao().testResultsByReport(reportId);
	}

	@Override
	public List<TestResult> testResultsByPatient(long patientId) {
		return getTestResultDao().testResultsByPatient(patientId);
	}

	@Override
	public TestResult testResultByTestAndPatient(long testId, long patientId) {
		return getTestResultDao().testResultByTestAndPatient(testId, patientId);
	}

	@Override
	public List<TestResult> allTestResults() {
		return getTestResultDao().allTestResults();
	}

	public TestResultDao getTestResultDao() {
		return testResultDao;
	}

	public void setTestResultDao(TestResultDao testResultDao) {
		this.testResultDao = testResultDao;
	}
}
