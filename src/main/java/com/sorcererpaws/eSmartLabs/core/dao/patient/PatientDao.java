package com.sorcererpaws.eSmartLabs.core.dao.patient;

import java.util.List;

import com.sorcererpaws.eSmartLabs.core.entity.clinic.Patient;

public interface PatientDao {

	public Patient createPatient(Patient patient);
	public Patient updatePatient(Patient patient);
	public Patient getPatient(long patientId);
	public boolean deletePatient(long patientId);
	public List<Patient> patientsByDoctor(long doctorId);
	public List<Patient> patientsByLab(long labId);
	public List<Patient> allPatients();
}
