package com.sorcererpaws.eSmartLabs.core.dao.doctor;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sorcererpaws.eSmartLabs.core.dao.doctor.DoctorDao;
import com.sorcererpaws.eSmartLabs.core.entity.clinic.Doctor;

@Repository
public class DoctorDaoImpl implements DoctorDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Doctor createDoctor(Doctor doctor) {
		getEntityManager().persist(doctor); 
		return doctor;
	}

	@Override
	public Doctor updateDoctor(Doctor doctor) {
		doctor = getEntityManager().merge(doctor);
		return doctor;
	}

	@Override
	public Doctor getDoctor(long doctorId) {
		return (Doctor) getEntityManager().find(Doctor.class, doctorId);
	}

	@Override
	public boolean deleteDoctor(long doctorId) {
		Doctor doctor = (Doctor) getEntityManager().find(Doctor.class, doctorId);
		getEntityManager().remove(doctor);
		return true;
	}

	@Override
	public List<Doctor> allDoctors() {
		TypedQuery<Doctor> typedQuery = getEntityManager().createQuery("FROM Doctor D", Doctor.class);
		return typedQuery.getResultList();
	}
	
	@Override
	public List<Doctor> doctorsByClinic(long clinicId) {
		
		TypedQuery<Doctor> typedQuery = getEntityManager()
				.createQuery("FROM Doctor D WHERE D.clinic.id = :clinicId", Doctor.class);
		typedQuery.setParameter("clinicId", clinicId);
		return typedQuery.getResultList();
	}
	
	@Override
	public List<Doctor> doctorsByLab(long labId) {
		
		String query = "FROM Doctor D WHERE D.clinic.id IN (SELECT C.id FROM Clinic C WHERE C.lab.id = :labId)";
		TypedQuery<Doctor> doctorsByLab = getEntityManager().createQuery(query, Doctor.class);
		doctorsByLab.setParameter("labId", labId);
		
		return doctorsByLab.getResultList();
	}

	//Getters and setters
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}