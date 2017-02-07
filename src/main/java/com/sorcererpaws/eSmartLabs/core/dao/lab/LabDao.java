package com.sorcererpaws.eSmartLabs.core.dao.lab;

import java.util.List;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Lab;

public interface LabDao {

	public Lab createLab(Lab lab);
	public Lab updateLab(Lab lab);
	public Lab getLab(long labId);
	public boolean deleteLab(long labId);
	public List<Lab> labs();
}
