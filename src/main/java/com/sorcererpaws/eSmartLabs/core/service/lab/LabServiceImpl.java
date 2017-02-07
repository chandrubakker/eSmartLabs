package com.sorcererpaws.eSmartLabs.core.service.lab;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sorcererpaws.eSmartLabs.core.dao.lab.LabDao;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Lab;

@Service
@Transactional(readOnly = false)
public class LabServiceImpl implements LabService {

	@Autowired
	private LabDao labDao;

	@Override
	public Lab updateLab(Lab lab) {
		return (lab.getId() >= 0 ? ( lab.getId() == 0 ? getLabDao().createLab(lab) : getLabDao().updateLab(lab)) : null);
	}

	@Override
	@Transactional(readOnly = true)
	public Lab getLab(long labId) {
		return (labId > 0 ? getLabDao().getLab(labId) : null);
	}

	@Override
	public boolean deleteLab(long labId) {
		return (labId > 0 ? getLabDao().deleteLab(labId) : false);
	}
	
	@Override
	public List<Lab> labs() {
		return getLabDao().labs();
	}
	
	//Getters and setters
	public LabDao getLabDao() {
		return labDao;
	}

	public void setLabDao(LabDao labDao) {
		this.labDao = labDao;
	}
}
