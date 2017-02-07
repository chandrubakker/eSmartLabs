package com.sorcererpaws.eSmartLabs.core.service.clinic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sorcererpaws.eSmartLabs.core.dao.clinic.ClinicDao;
import com.sorcererpaws.eSmartLabs.core.entity.clinic.Clinic;

@Service
@Transactional(readOnly = true)
public class ClinicServiceImpl implements ClinicService {

	@Autowired
	private ClinicDao clinicDao;

	@Override
	@Transactional(readOnly = false)
	public Clinic createClinic(Clinic clinic) {
		return (clinic.getId() == 0 ? getClinicDao().createClinic(clinic) : null);
	}

	@Override
	@Transactional(readOnly = false)
	public Clinic updateClinic(Clinic clinic) {
		return (clinic.getId() > 0 ? getClinicDao().updateClinic(clinic) : null);
	}

	@Override
	public Clinic getClinic(long clinicId) {
		return (clinicId > 0 ? getClinicDao().getClinic(clinicId) : null);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteClinic(long clinicId) {
		return (clinicId > 0 ? getClinicDao().deleteClinic(clinicId) : false);
	}

	@Override
	public List<Clinic> clinics() {
		return getClinicDao().clinics();
	}

	@Override
	public List<Clinic> clinicsByLab(long labId) {
		return getClinicDao().clinicsByLab(labId);
	}
	
	//Getters and setters
	public ClinicDao getClinicDao() {
		return clinicDao;
	}

	public void setClinicDao(ClinicDao clinicDao) {
		this.clinicDao = clinicDao;
	}
}
