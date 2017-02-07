package com.sorcererpaws.eSmartLabs.core.dao.lab;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Lab;

@Repository
public class LabDaoImpl implements LabDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Lab createLab(Lab lab) {
		getEntityManager().persist(lab); 
		return lab;
	}

	@Override
	public Lab updateLab(Lab lab) {
		lab = getEntityManager().merge(lab);
		return lab;
	}

	@Override
	public Lab getLab(long labId) {
		return (Lab) getEntityManager().find(Lab.class, labId);
	}

	@Override
	public boolean deleteLab(long labId) {
		Lab lab = (Lab) getEntityManager().find(Lab.class, labId);
		getEntityManager().remove(lab);
		return true;
	}
	
	@Override
	public List<Lab> labs() {
		
		TypedQuery<Lab> labs = getEntityManager().createQuery("FROM Lab L", Lab.class);
		return labs.getResultList();
	}

	//Getters and setters
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
