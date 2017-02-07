package com.sorcererpaws.eSmartLabs.core.dao.company;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Company;

public interface CompanyDao {

	public Company addCompany(Company company);
	public Company updateCompany(Company company);
	public Company getCompany(Long companyId);
}
