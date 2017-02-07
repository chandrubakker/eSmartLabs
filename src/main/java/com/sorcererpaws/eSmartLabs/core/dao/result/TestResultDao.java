package com.sorcererpaws.eSmartLabs.core.dao.result;

import java.util.List;

import com.sorcererpaws.eSmartLabs.core.entity.lab.TestResult;

public interface TestResultDao {

	public TestResult createTestResult(TestResult testResult);
	public TestResult updateTestResult(TestResult testResult);
	public TestResult getTestResult(long testResultId);
	public boolean deleteTestResult(long testResultId);
	public List<TestResult> testResultsByReport(long reportId);
	public List<TestResult> testResultsByPatient(long patientId);
	public TestResult testResultByTestAndPatient(long testId, long patientId);
	public List<TestResult> allTestResults();
}
