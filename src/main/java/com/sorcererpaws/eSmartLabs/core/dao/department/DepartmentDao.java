package com.sorcererpaws.eSmartLabs.core.dao.department;

import java.util.List;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Department;

public interface DepartmentDao {

	public Department createDepartment(Department department);
	public Department updateDepartment(Department department);
	public Department getDepartment(long departmentId);
	public boolean deleteDepartment(long departmentId);
	public boolean isDeptExists(String deptCode); 
	public List<Department> departmentsByLab(long labId);
	public List<Department> departments();
}
