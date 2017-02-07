package com.sorcererpaws.eSmartLabs.core.service.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sorcererpaws.eSmartLabs.core.dao.company.CompanyDao;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Company;

@Service
@Transactional(readOnly = true)
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDao companyDao;

	@Override
	@Transactional(readOnly = false)
	public Company addCompany(Company company) {
		return getCompanyDao().addCompany(company);
	}

	@Override
	@Transactional(readOnly = false)
	public Company updateCompany(Company company) {
		return getCompanyDao().updateCompany(company);
	}

	@Override
	public Company getCompany(Long companyId) {
		return getCompanyDao().getCompany(companyId);
	}

	//Getters and setters
	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}
}
