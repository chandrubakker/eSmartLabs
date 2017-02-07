package com.sorcererpaws.eSmartLabs.core.service.lab;

import java.util.List;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Lab;

public interface LabService {

	public Lab updateLab(Lab lab);
	public Lab getLab(long labId);
	public boolean deleteLab(long labId);
	public List<Lab> labs();
}
