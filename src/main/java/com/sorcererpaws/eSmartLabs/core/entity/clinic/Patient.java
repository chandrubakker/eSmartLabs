package com.sorcererpaws.eSmartLabs.core.entity.clinic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Report;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Test;

@Entity
public class Patient {

	private long id;
	private String regNum;
	private String name;
	private String citation;
	private String gender;
	private double age;
	private Date dateOfAdmission;
	private String status;
	private Doctor referredDoctor;
	private boolean selfInterest;
	private String shortAddress;
	private String phone; 
	private Report report;
	private List<Test> tests = new ArrayList<Test>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getRegNum() {
		return regNum;
	}
	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCitation() {
		return citation;
	}
	public void setCitation(String citation) {
		this.citation = citation;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public double getAge() {
		return age;
	}
	public void setAge(double age) {
		this.age = age;
	}
	
	@DateTimeFormat(pattern = "dd/MM/yyyy", iso = ISO.DATE)
	public Date getDateOfAdmission() {
		return dateOfAdmission;
	}
	public void setDateOfAdmission(Date dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "doctorId")
	public Doctor getReferredDoctor() {
		return referredDoctor;
	}
	public void setReferredDoctor(Doctor referredDoctor) {
		this.referredDoctor = referredDoctor;
	}
	
	public boolean isSelfInterest() {
		return selfInterest;
	}
	public void setSelfInterest(boolean selfInterest) {
		this.selfInterest = selfInterest;
	}
	
	public String getShortAddress() {
		return shortAddress;
	}
	public void setShortAddress(String shortAddress) {
		this.shortAddress = shortAddress;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@JsonIgnore
	@OneToOne(mappedBy = "patient")
	public Report getReport() {
		return report;
	}
	public void setReport(Report report) {
		this.report = report;
	}
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "Test_Patient", joinColumns = {@JoinColumn(name = "patientId")}, 
	inverseJoinColumns = {@JoinColumn(name = "testId")})
	public List<Test> getTests() {
		return tests;
	}
	public void setTests(List<Test> tests) {
		this.tests = tests;
	}
}
