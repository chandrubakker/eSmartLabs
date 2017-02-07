package com.sorcererpaws.eSmartLabs.core.service.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sorcererpaws.eSmartLabs.core.dao.report.ReportDao;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Report;

@Service
@Transactional(readOnly = true)
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportDao reportDao;

	@Override
	@Transactional(readOnly = false)
	public Report createReport(Report report) {
		return getReportDao().createReport(report);
	}

	@Override
	@Transactional(readOnly = false)
	public Report updateReport(Report report) {
		return getReportDao().updateReport(report);
	}

	@Override
	public Report getReport(long reportId) {
		return (reportId > 0 ? getReportDao().getReport(reportId) : null);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteReport(long reportId) {
		return (reportId > 0 ? getReportDao().deleteReport(reportId) : false);
	}

	@Override
	public Report reportByPatient(long patientId) {
		return (patientId > 0 ? getReportDao().reportByPatient(patientId) : null);
	}

	@Override
	public List<Report> allReports() {
		return getReportDao().allReports();
	}

	//Getters and setters
	public ReportDao getReportDao() {
		return reportDao;
	}

	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
}
