package com.sorcererpaws.eSmartLabs.core.dao.company;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Company;

@Repository
public class CompanyDaoImpl implements CompanyDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Company addCompany(Company company) {
		
		getEntityManager().persist(company);
		return company;
	}

	@Override
	public Company updateCompany(Company company) {
		
		company = (Company) getEntityManager().merge(company);
		return company;
	}

	@Override
	public Company getCompany(Long companyId) {
		
		return (Company) getEntityManager().find(Company.class, companyId);
	}

	//Getters and setters
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
