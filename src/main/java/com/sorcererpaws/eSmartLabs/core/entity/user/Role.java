package com.sorcererpaws.eSmartLabs.core.entity.user;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role {

	private Long id;
	private String name;
	
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
}
