package com.sorcererpaws.eSmartLabs.core.entity.lab;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sorcererpaws.eSmartLabs.core.entity.clinic.Patient;

@Entity
public class TestResult {

	private long id;
	private Patient patient;
	private Test test;
	private String observedValue;
	private Report report;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "patientId")
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "testId")
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
		this.test = test;
	}
	
	public String getObservedValue() {
		return observedValue;
	}
	public void setObservedValue(String observedValue) {
		this.observedValue = observedValue;
	}
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "reportId")
	public Report getReport() {
		return report;
	}
	public void setReport(Report report) {
		this.report = report;
	}
}
