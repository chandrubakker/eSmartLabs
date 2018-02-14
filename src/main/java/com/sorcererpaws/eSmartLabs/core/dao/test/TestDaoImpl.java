package com.sorcererpaws.eSmartLabs.core.dao.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Test;
import com.sorcererpaws.eSmartLabs.core.entity.lab.TestGroup;
import com.sorcererpaws.eSmartLabs.core.entity.respo.CustomTest;

@Repository
@SuppressWarnings("unchecked")
public class TestDaoImpl implements TestDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestDaoImpl.class);
	
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
	public List<CustomTest> customTestsByDepartment(long departmentId) {
		
		/*String query = "SELECT NEW com.sorcererpaws.eSmartLabs.core.entity.respo.CustomTest(T.id, D.id, L.id, T.code, T.name, D.name, T.price";
		query += ", T.normalMin, T.normalMax, T.unitType, T.unitUsed, T.rangesSameForAll, T.maleMin, T.maleMax, T.femaleMin, T.femaleMax, T.childMin, T.childMax, L.name)";
		query += " FROM ((Test T INNER JOIN Department D ON T.department.id = D.id AND T.department.id = :departmentId) INNER JOIN Lab L ON D.lab.id = L.id)";*/
		
		String query = "SELECT NEW com.sorcererpaws.eSmartLabs.core.entity.respo.CustomTest(T.id, D.id, L.id, T.code, T.name, D.name, T.price";
		query += ", T.normalMin, T.normalMax, T.unitType, T.unitUsed, T.rangesSameForAll, T.maleMin, T.maleMax, T.femaleMin, T.femaleMax, T.childMin, T.childMax, L.name)";
		query += " FROM Test T JOIN T.department D JOIN D.lab L WHERE D.lab.id = L.id AND T.department.id = :departmentId";
		
		LOGGER.info("Query Executed:");
		LOGGER.info(query);
		
		TypedQuery<CustomTest> typedQuery = getEntityManager().createQuery(query, CustomTest.class);
		typedQuery.setParameter("departmentId", departmentId);
		
		return typedQuery.getResultList();
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

	@Override
	public TestGroup createTestGroup(TestGroup testGroup) {
		
		getEntityManager().merge(testGroup);
		return testGroup;
	}

	@Override
	public TestGroup getTestGroup(Long testGroupId) {
		
		return getEntityManager().find(TestGroup.class, testGroupId);
	}

	@Override
	public TestGroup updateTestGroup(TestGroup testGroup) {
		
		testGroup = (TestGroup) getEntityManager().merge(testGroup);
		return testGroup;
	}

	@Override
	public boolean deleteTestGroup(Long testGroupId) {
		
		return getEntityManager()
				.createQuery("DELETE FROM TestGroup TG WHERE TG.id = :testGroupId")
				.setParameter("testGroupId", testGroupId)
				.executeUpdate() > 0;
	}

	@Override
	public List<TestGroup> testGroups() {
		
		return getEntityManager()
				.createQuery("FROM TestGroup TG", TestGroup.class)
				.getResultList();
	}

	@Override
	public List<Test> testsByTestGroup(Long testGroupId) {
		
		Query query = getEntityManager()
				.createQuery("SELECT TG.tests FROM TestGroup TG WHERE TG.id = :testGroupId");
		query.setParameter("testGroupId", testGroupId);
		return query.getResultList();
	}

	@Override
	public List<TestGroup> testGroupsByLab(long labId) {
		
		String query = "SELECT DISTINCT T.testGroups FROM Test T WHERE T.department.id IN ";
		query = query + "(SELECT D.id FROM Department D WHERE D.lab.id = :labId)";
		
		Query testGroupsByLab = getEntityManager().createQuery(query);
		testGroupsByLab.setParameter("labId", labId);
		
		return testGroupsByLab.getResultList();
	}

	//Getters and setters
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
