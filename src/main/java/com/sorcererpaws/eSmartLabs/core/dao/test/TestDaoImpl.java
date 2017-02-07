package com.sorcererpaws.eSmartLabs.core.dao.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Test;

@Repository
@SuppressWarnings("unchecked")
public class TestDaoImpl implements TestDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Test createTest(Test test) {
		getEntityManager().persist(test);
		return test;
	}

	@Override
	public Test updateTest(Test test) {
		test = (Test) getEntityManager().merge(test);
		return test;
	}

	@Override
	public Test getTest(long testId) {
		return (Test) getEntityManager().find(Test.class, testId);
	}

	@Override
	public boolean deleteTest(long testId) {
		
		Query query = getEntityManager().createQuery("DELETE FROM Test T WHERE T.id = :testId");
		query.setParameter("testId", testId);
		return (query.executeUpdate() > 0 ? true : false);
	}

	@Override
	public List<Test> testsByPatient(long patientId) {
		Query query = getEntityManager()
				.createQuery("SELECT P.tests FROM Patient P WHERE P.id = :patientId");
		query.setParameter("patientId", patientId);
		return query.getResultList();
	}

	@Override
	public List<Test> testsByDepartment(long departmentId) {
		Query query = getEntityManager()
				.createQuery("FROM Test T WHERE T.department.id = :departmentId");
		query.setParameter("departmentId", departmentId);
		return query.getResultList();
	}

	@Override
	public List<Test> testsByLab(long labId) {
		
		String query = "FROM Test T WHERE T.department.id IN (SELECT D.id FROM Department D WHERE D.lab.id = :labId)";
		TypedQuery<Test> testsByLab = getEntityManager().createQuery(query, Test.class);
		testsByLab.setParameter("labId", labId);
		
		return testsByLab.getResultList();
	}

	@Override
	public List<Test> allTests() {
		TypedQuery<Test> typedQuery = getEntityManager().createQuery("FROM Test T", Test.class);
		return typedQuery.getResultList();
	}

	@Override
	public boolean isTestExists(String testCode) {
		TypedQuery<Test> typedQuery = getEntityManager()
				.createQuery("FROM Test T WHERE T.code = :testCode", Test.class);
		typedQuery.setParameter("testCode", testCode);
		return (typedQuery.getResultList().isEmpty() ? false : true);
	}

	//Getters and setters
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
