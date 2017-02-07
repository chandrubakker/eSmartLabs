package com.sorcererpaws.eSmartLabs.core.dao.result;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sorcererpaws.eSmartLabs.core.entity.lab.TestResult;

@Repository
public class TestResultDaoImpl implements TestResultDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public TestResult createTestResult(TestResult testResult) {
		getEntityManager().persist(testResult);
		return testResult;
	}

	@Override
	public TestResult updateTestResult(TestResult testResult) {
		testResult = getEntityManager().merge(testResult);
		return testResult;
	}

	@Override
	public TestResult getTestResult(long testResultId) {
		return getEntityManager().find(TestResult.class, testResultId);
	}

	@Override
	public boolean deleteTestResult(long testResultId) {
		
		Query query = getEntityManager().createQuery("DELETE FROM TestResult TR WHERE TR.id = :testResultId");
		query.setParameter("testResultId", testResultId);
		return (query.executeUpdate() > 0 ? true : false);
	}

	@Override
	public List<TestResult> testResultsByReport(long reportId) {
		
		TypedQuery<TestResult> testResultsByReport = getEntityManager()
				.createQuery("FROM TestResult TR WHERE TR.report.id = :reportId", TestResult.class);
		testResultsByReport.setParameter("reportId", reportId);
		return testResultsByReport.getResultList();
	}

	@Override
	public List<TestResult> testResultsByPatient(long patientId) {
		
		TypedQuery<TestResult> testResultsByPatiet = getEntityManager()
				.createQuery("FROM TestResult TR WHERE TR.patient.id = :patientId", TestResult.class);
		testResultsByPatiet.setParameter("patientId", patientId);
		return testResultsByPatiet.getResultList();
	}

	@Override
	public TestResult testResultByTestAndPatient(long testId, long patientId) {
		return null;
	}

	@Override
	public List<TestResult> allTestResults() {
		
		return getEntityManager()
				.createQuery("FROM TestResult TR", TestResult.class)
				.getResultList();
	}

	//Getters and setters
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
