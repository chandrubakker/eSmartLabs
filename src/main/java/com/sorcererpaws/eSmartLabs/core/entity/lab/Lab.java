package com.sorcererpaws.eSmartLabs.core.entity.lab;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sorcererpaws.eSmartLabs.core.entity.clinic.Clinic;
import com.sorcererpaws.eSmartLabs.core.entity.common.Address;

@Entity
public class Lab {

	private long id;
	private String name;
	private String info;
	private Date estdDate;
	private Address address;
	private List<Department> departments = new ArrayList<Department>();
	private List<Clinic> clinics = new ArrayList<Clinic>();
	private Client client;
	
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
	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	@DateTimeFormat(pattern = "dd/MM/yyyy", iso = ISO.DATE)
	public Date getEstdDate() {
		return estdDate;
	}
	public void setEstdDate(Date estdDate) {
		this.estdDate = estdDate;
	}
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "addressId")
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "lab")
	public List<Department> getDepartments() {
		return departments;
	}
	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "lab")
	public List<Clinic> getClinics() {
		return clinics;
	}
	public void setClinics(List<Clinic> clinics) {
		this.clinics = clinics;
	}
	
	@OneToOne(mappedBy = "lab")
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
}
