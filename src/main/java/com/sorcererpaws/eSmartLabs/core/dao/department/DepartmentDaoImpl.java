package com.sorcererpaws.eSmartLabs.core.dao.department;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Department;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Department createDepartment(Department department) {
		getEntityManager().persist(department);
		return department;
	}

	@Override
	public Department updateDepartment(Department department) {
		department = (Department) getEntityManager().merge(department);
		return department;
	}

	@Override
	public Department getDepartment(long departmentId) {
		return (Department) getEntityManager().find(Department.class, departmentId);
	}

	@Override
	public boolean deleteDepartment(long departmentId) {
		Department department = (Department) getEntityManager().find(Department.class, departmentId);
		getEntityManager().remove(department);
		return true;
	}

	@Override
	public boolean isDeptExists(String deptCode) {
		
		TypedQuery<Department> typedQuery = getEntityManager()
				.createQuery("FROM Department D WHERE D.code = :deptCode", Department.class);
		typedQuery.setParameter("deptCode", deptCode);
		return (typedQuery.getResultList().isEmpty() ? false : true);
	}
	
	@Override
	public List<Department> departmentsByLab(long labId) {
		
		TypedQuery<Department> deptsByLab = getEntityManager()
				.createQuery("FROM Department D WHERE D.lab.id = :labId", Department.class);
		deptsByLab.setParameter("labId", labId);
		
		return deptsByLab.getResultList();
	}

	@Override
	public List<Department> departments() {
		TypedQuery<Department> typedQuery = getEntityManager().createQuery("FROM Department D", Department.class);
		return typedQuery.getResultList();
	}

	//Getters and setters
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
