package com.sorcererpaws.eSmartLabs.core.entity.lab;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sorcererpaws.eSmartLabs.core.entity.common.Address;
import com.sorcererpaws.eSmartLabs.core.entity.user.User;

@Entity
public class Company {

	private Long id;
	private String name;
	private String info;
	private Date estdDate;
	private Address address;
	private User user;
	private List<Client> clients = new ArrayList<Client>();
	
	@Id
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	
	@DateTimeFormat(pattern = "MM/yyyy")
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
	@OneToOne
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "company")
	public List<Client> getClients() {
		return clients;
	}
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
}
