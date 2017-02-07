package com.sorcererpaws.eSmartLabs.core.dao.clinic;

import java.util.List;

import com.sorcererpaws.eSmartLabs.core.entity.clinic.Clinic;

public interface ClinicDao {

	public Clinic createClinic(Clinic clinic);
	public Clinic updateClinic(Clinic clinic);
	public Clinic getClinic(long clinicId);
	public boolean deleteClinic(long clinicId);
	public List<Clinic> clinics();
	public List<Clinic> clinicsByLab(long labId);
}
