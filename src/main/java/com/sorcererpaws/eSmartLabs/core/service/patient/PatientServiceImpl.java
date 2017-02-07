package com.sorcererpaws.eSmartLabs.core.service.patient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sorcererpaws.eSmartLabs.core.dao.patient.PatientDao;
import com.sorcererpaws.eSmartLabs.core.entity.clinic.Patient;

@Service
@Transactional(readOnly = true)
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientDao patientDao;

	@Override
	@Transactional(readOnly = false)
	public Patient createPatient(Patient patient) {
		return (patient.getId() == 0 ? getPatientDao().createPatient(patient) : null);
	}

	@Override
	@Transactional(readOnly = false)
	public Patient updatePatient(Patient patient) {
		return (patient.getId() > 0 ? getPatientDao().updatePatient(patient) : null);
	}

	@Override
	public Patient getPatient(long patientId) {
		return (patientId > 0 ? getPatientDao().getPatient(patientId) : null);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deletePatient(long patientId) {
		return (patientId > 0 ? getPatientDao().deletePatient(patientId) : false);
	}

	@Override
	public List<Patient> patientsByDoctor(long doctorId) {
		return getPatientDao().patientsByDoctor(doctorId);
	}

	@Override
	public List<Patient> patientsByLab(long labId) {
		return getPatientDao().patientsByLab(labId);
	}
	
	@Override
	public List<Patient> allPatients() {
		return getPatientDao().allPatients();
	}

	//Getters and setters
	public PatientDao getPatientDao() {
		return patientDao;
	}

	public void setPatientDao(PatientDao patientDao) {
		this.patientDao = patientDao;
	}
}
