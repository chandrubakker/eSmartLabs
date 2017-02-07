package com.sorcererpaws.eSmartLabs.core.dao.geo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sorcererpaws.eSmartLabs.core.entity.common.Address;

@Repository
public class GeoDaoImpl implements GeoDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Address createAddress(Address address) {
		getEntityManager().persist(address); 
		return address;
	}

	@Override
	public Address updateAddress(Address address) {
		address = getEntityManager().merge(address);
		return address;
	}

	@Override
	public Address getAddress(long addressId) {
		return (Address) getEntityManager().find(Address.class, addressId);
	}

	@Override
	public boolean deleteAddress(long addressId) {
		Address address = (Address) getEntityManager().find(Address.class, addressId);
		getEntityManager().remove(address);
		return true;
	}

	@Override
	public List<Address> addresses() {
		TypedQuery<Address> typedQuery = getEntityManager().createQuery("FROM Address A", Address.class);
		return typedQuery.getResultList();
	}

	//Getters and setters
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
