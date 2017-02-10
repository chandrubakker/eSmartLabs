package com.sorcererpaws.eSmartLabs.core.dao.report;

import java.util.List;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Report;

public interface ReportDao {

	public Report createReport(Report report);
	public Report updateReport(Report report);
	public Report getReport(long reportId);
	public boolean deleteReport(long reportId);
	public Report reportByPatient(long patientId);
	public List<Report> reportsByLab(long labId);
	public List<Report> allReports();
}
