package com.sorcererpaws.eSmartLabs.core.entity.clinic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sorcererpaws.eSmartLabs.core.entity.common.Address;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Lab;

@Entity
public class Clinic {

	private long id;
	private String name;
	private String info;
	private Address address;
	private Lab lab;
	private List<Doctor> doctors = new ArrayList<Doctor>();
	
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
	
	@OneToOne
	@JoinColumn(name = "addressId")
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@ManyToOne
	@JoinColumn(name = "labId")
	public Lab getLab() {
		return lab;
	}
	public void setLab(Lab lab) {
		this.lab = lab;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "clinic")
	public List<Doctor> getDoctors() {
		return doctors;
	}
	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}
}
