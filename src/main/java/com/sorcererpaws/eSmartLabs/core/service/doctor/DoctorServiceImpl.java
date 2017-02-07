package com.sorcererpaws.eSmartLabs.core.service.doctor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sorcererpaws.eSmartLabs.core.dao.doctor.DoctorDao;
import com.sorcererpaws.eSmartLabs.core.entity.clinic.Doctor;

@Service
@Transactional(readOnly = true)
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorDao doctorDao;

	@Override
	@Transactional(readOnly = false)
	public Doctor createDoctor(Doctor doctor) {
		return (doctor.getId() == 0 ? getDoctorDao().createDoctor(doctor) : null);
	}

	@Override
	@Transactional(readOnly = false)
	public Doctor updateDoctor(Doctor doctor) {
		return (doctor.getId() > 0 ? getDoctorDao().updateDoctor(doctor) : null);
	}

	@Override
	public Doctor getDoctor(long doctorId) {
		return (doctorId > 0 ? getDoctorDao().getDoctor(doctorId) : null);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteDoctor(long doctorId) {
		return (doctorId > 0 ? getDoctorDao().deleteDoctor(doctorId) : false);
	}

	@Override
	public List<Doctor> doctorsByClinic(long clinicId) {
		return getDoctorDao().doctorsByClinic(clinicId);
	}

	@Override
	public List<Doctor> doctorsByLab(long labId) {
		return getDoctorDao().doctorsByLab(labId);
	}
	
	@Override
	public List<Doctor> allDoctors() {
		return getDoctorDao().allDoctors();
	}

	//Getters and setters
	public DoctorDao getDoctorDao() {
		return doctorDao;
	}

	public void setDoctorDao(DoctorDao doctorDao) {
		this.doctorDao = doctorDao;
	}
}
