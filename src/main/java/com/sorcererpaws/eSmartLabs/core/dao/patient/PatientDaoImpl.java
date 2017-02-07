package com.sorcererpaws.eSmartLabs.core.dao.patient;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sorcererpaws.eSmartLabs.core.entity.clinic.Patient;

@Repository
public class PatientDaoImpl implements PatientDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Patient createPatient(Patient patient) {
		getEntityManager().merge(patient);
		return patient;
	}

	@Override
	public Patient updatePatient(Patient patient) {
		patient = (Patient) getEntityManager().merge(patient);
		return patient;
	}

	@Override
	public Patient getPatient(long patientId) {
		return (Patient) getEntityManager().find(Patient.class, patientId);
	}

	@Override
	public boolean deletePatient(long patientId) {
		return ((getEntityManager().createQuery("DELETE FROM Patient P WHERE P.id = :patientId")
				.setParameter("patientId", patientId)
				.executeUpdate()) > 0 ? true : false);
	}

	@Override
	public List<Patient> patientsByDoctor(long doctorId) {
		TypedQuery<Patient> typedQuery = getEntityManager()
				.createQuery("FROM Patient P WHERE p.referredDoctor.id = :doctorId", Patient.class);
		typedQuery.setParameter("doctorId", doctorId);
		return typedQuery.getResultList();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Patient> patientsByLab(long labId) {

		String query = "SELECT DISTINCT T.patients FROM Test T WHERE T.department.id IN ";
		query = query + "(SELECT D.id FROM Department D WHERE D.lab.id = :labId)";
		
		Query patientsByLab = getEntityManager().createQuery(query);
		patientsByLab.setParameter("labId", labId);
		return patientsByLab.getResultList();
	}

	@Override
	public List<Patient> allPatients() {
		TypedQuery<Patient> typedQuery = getEntityManager()
				.createQuery("FROM Patient P", Patient.class);
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
