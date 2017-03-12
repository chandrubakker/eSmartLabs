package com.sorcererpaws.eSmartLabs.core.dao.contact;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sorcererpaws.eSmartLabs.core.entity.common.Contact;

@Repository
public class ContactDaoImpl implements ContactDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Contact addContact(Contact contact) {
		getEntityManager().persist(contact);
		return contact;
	}

	@Override
	public Contact getContact(Long contactId) {
		return getEntityManager().find(Contact.class, contactId);
	}

	@Override
	public boolean deleteContact(Long contactId) {
		
		String query = "DELETE FROM Contact C WHERE C.id = :contactId";
		Query deleteContact = getEntityManager().createQuery(query);
		deleteContact.setParameter("contactId", contactId);
		
		return (deleteContact.executeUpdate() > 0 ? true : false);
	}

	@Override
	public List<Contact> contacts() {
		
		TypedQuery<Contact> contacts = getEntityManager().createQuery("FROM Contact C", Contact.class);
		return contacts.getResultList();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
