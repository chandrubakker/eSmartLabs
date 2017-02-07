package com.sorcererpaws.eSmartLabs.core.dao.report;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Report;

@Repository
public class ReportDaoImpl implements ReportDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Report createReport(Report report) {
		getEntityManager().persist(report);
		return report;
	}

	@Override
	public Report updateReport(Report report) {
		report = (Report) getEntityManager().merge(report);
		return report;
	}

	@Override
	public Report getReport(long reportId) {
		return (Report) getEntityManager().find(Report.class, reportId);
	}

	@Override
	public boolean deleteReport(long reportId) {
		return ((getEntityManager()
				.createQuery("DELETE FROM Report R WHERE R.id = :reportId")
				.setParameter("reportId", reportId).executeUpdate()) > 0 ? true : false);
	}

	@Override
	public Report reportByPatient(long patientId) {
		TypedQuery<Report> typedQuery = getEntityManager().createQuery("FROM Report R WHERE R.patient.id = :patientId", Report.class);
		typedQuery.setParameter("patientId", patientId);
		
		try {
			
			return typedQuery.getSingleResult();
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Report> allReports() {
		TypedQuery<Report> typedQuery = getEntityManager().createQuery("FROM Report R", Report.class);
		return typedQuery.getResultList();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
