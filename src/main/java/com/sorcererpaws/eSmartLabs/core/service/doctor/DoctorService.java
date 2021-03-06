package com.sorcererpaws.eSmartLabs.core.service.doctor;

import java.util.List;

import com.sorcererpaws.eSmartLabs.core.entity.clinic.Doctor;

public interface DoctorService {

	public Doctor createDoctor(Doctor doctor);
	public Doctor updateDoctor(Doctor doctor);
	public Doctor getDoctor(long doctorId);
	public boolean deleteDoctor(long doctorId);
	public List<Doctor> doctorsByClinic(long clinicId);
	public List<Doctor> doctorsByLab(long labId);
	public List<Doctor> allDoctors();
}
