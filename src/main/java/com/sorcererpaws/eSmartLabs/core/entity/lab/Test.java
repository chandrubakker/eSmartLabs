package com.sorcererpaws.eSmartLabs.core.entity.lab;

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
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sorcererpaws.eSmartLabs.core.entity.clinic.Patient;
import com.sorcererpaws.eSmartLabs.core.serializer.DateTimeSerializer;

@Entity
public class Test {

	private long id;
	private String name;
	private String code;
	
	private String normalMin;
	private String normalMax;
	private String unitType; 
	private String unitUsed;
	private double price;
	private String select;
	
	private boolean rangesSameForAll = true;
	
	private String maleMin;
	private String maleMax;
	
	private String femaleMin;
	private String femaleMax;
	
	private String childMin;
	private String childMax;
	
	private Date lastUpdatedDate;
	private List<Patient> patients = new ArrayList<Patient>();
	private Department department;
	private List<TestGroup> testGroups = new ArrayList<TestGroup>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getNormalMin() {
		return normalMin;
	}
	public void setNormalMin(String normalMin) {
		this.normalMin = normalMin;
	}
	
	public String getNormalMax() {
		return normalMax;
	}
	public void setNormalMax(String normalMax) {
		this.normalMax = normalMax;
	}
	
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	
	public String getUnitUsed() {
		return unitUsed;
	}
	public void setUnitUsed(String unitUsed) {
		this.unitUsed = unitUsed;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Transient
	public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}
	
	public boolean isRangesSameForAll() {
		return rangesSameForAll;
	}
	public void setRangesSameForAll(boolean rangesSameForAll) {
		this.rangesSameForAll = rangesSameForAll;
	}
	public String getMaleMin() {
		return maleMin;
	}
	public void setMaleMin(String maleMin) {
		this.maleMin = maleMin;
	}
	public String getMaleMax() {
		return maleMax;
	}
	public void setMaleMax(String maleMax) {
		this.maleMax = maleMax;
	}
	public String getFemaleMin() {
		return femaleMin;
	}
	public void setFemaleMin(String femaleMin) {
		this.femaleMin = femaleMin;
	}
	public String getFemaleMax() {
		return femaleMax;
	}
	public void setFemaleMax(String femaleMax) {
		this.femaleMax = femaleMax;
	}
	public String getChildMin() {
		return childMin;
	}
	public void setChildMin(String childMin) {
		this.childMin = childMin;
	}
	public String getChildMax() {
		return childMax;
	}
	public void setChildMax(String childMax) {
		this.childMax = childMax;
	}
	@JsonSerialize(using = DateTimeSerializer.class)
	@DateTimeFormat(pattern = "dd/MM/yyyy", iso = ISO.DATE)
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "Test_Patient", joinColumns = {@JoinColumn(name = "testId")}, 
	inverseJoinColumns = {@JoinColumn(name = "patientId")})
	public List<Patient> getPatients() {
		return patients;
	}
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "departmentId")
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "TestGroup_Test", joinColumns = {@JoinColumn(name = "testId")}, 
	inverseJoinColumns = {@JoinColumn(name = "testGroupId")})
	public List<TestGroup> getTestGroups() {
		return testGroups;
	}
	public void setTestGroups(List<TestGroup> testGroups) {
		this.testGroups = testGroups;
	}
}
