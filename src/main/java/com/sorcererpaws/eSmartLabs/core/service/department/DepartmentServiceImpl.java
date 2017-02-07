package com.sorcererpaws.eSmartLabs.core.service.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sorcererpaws.eSmartLabs.core.dao.department.DepartmentDao;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Department;

@Service
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;

	@Override
	@Transactional(readOnly = false)
	public Department createDepartment(Department department) {
		return (department.getId() == 0 ? getDepartmentDao().createDepartment(department) : null);
	}

	@Override
	@Transactional(readOnly = false)
	public Department updateDepartment(Department department) {
		return (department.getId() > 0 ? getDepartmentDao().updateDepartment(department) : null);
	}

	@Override
	public Department getDepartment(long departmentId) {
		return (departmentId > 0 ? getDepartmentDao().getDepartment(departmentId) : null);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteDepartment(long departmentId) {
		return (departmentId > 0 ? getDepartmentDao().deleteDepartment(departmentId) : false);
	}

	@Override
	public List<Department> departmentsByLab(long labId) {
		return getDepartmentDao().departmentsByLab(labId);
	}
	
	@Override
	public List<Department> departments() {
		return getDepartmentDao().departments();
	}
	
	@Override
	public boolean isDeptExists(String deptCode) {
		return (deptCode != null ? getDepartmentDao().isDeptExists(deptCode) : false);
	}

	//Getters and setters
	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
}
