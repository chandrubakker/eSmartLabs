package com.sorcererpaws.eSmartLabs.core.service.company;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Company;

public interface CompanyService {

	public Company addCompany(Company company);
	public Company updateCompany(Company company);
	public Company getCompany(Long companyId);
}
