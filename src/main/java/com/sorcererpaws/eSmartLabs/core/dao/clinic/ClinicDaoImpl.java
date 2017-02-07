package com.sorcererpaws.eSmartLabs.core.dao.clinic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sorcererpaws.eSmartLabs.core.entity.clinic.Clinic;

@Repository
public class ClinicDaoImpl implements ClinicDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Clinic createClinic(Clinic clinic) {
		getEntityManager().persist(clinic); 
		return clinic;
	}

	@Override
	public Clinic updateClinic(Clinic clinic) {
		clinic = getEntityManager().merge(clinic);
		return clinic;
	}

	@Override
	public Clinic getClinic(long clinicId) {
		return (Clinic) getEntityManager().find(Clinic.class, clinicId);
	}

	@Override
	public boolean deleteClinic(long clinicId) {
		Clinic clinic = (Clinic) getEntityManager().find(Clinic.class, clinicId);
		getEntityManager().remove(clinic);
		return true;
	}

	@Override
	public List<Clinic> clinics() {
		TypedQuery<Clinic> typedQuery = getEntityManager().createQuery("FROM Clinic C", Clinic.class);
		return typedQuery.getResultList();
	}

	@Override
	public List<Clinic> clinicsByLab(long labId) {
		
		TypedQuery<Clinic> clinicsByLab = getEntityManager()
				.createQuery("FROM Clinic C WHERE C.lab.id = :labId", Clinic.class);
		clinicsByLab.setParameter("labId", labId);
		return clinicsByLab.getResultList();
	}

	//Getters and setters
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
