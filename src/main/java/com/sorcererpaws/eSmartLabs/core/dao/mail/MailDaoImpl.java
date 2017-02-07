package com.sorcererpaws.eSmartLabs.core.dao.mail;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sorcererpaws.eSmartLabs.core.entity.password.EmailLink;

@Repository
public class MailDaoImpl implements MailDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public EmailLink addEmailLink(EmailLink clinic) {
		getEntityManager().persist(clinic); 
		return clinic;
	}

	@Override
	public EmailLink updateEmailLink(EmailLink clinic) {
		clinic = getEntityManager().merge(clinic);
		return clinic;
	}

	@Override
	public EmailLink getEmailLink(long clinicId) {
		return (EmailLink) getEntityManager().find(EmailLink.class, clinicId);
	}

	@Override
	public boolean deleteEmailLink(long clinicId) {
		EmailLink clinic = (EmailLink) getEntityManager().find(EmailLink.class, clinicId);
		getEntityManager().remove(clinic);
		return true;
	}
	
	@Override
	public EmailLink getEmailLinkByUserAndToken(long userId, String token) {
		String query = "SELECT E FROM EmailLink E WHERE E.user.id = :userId AND E.otrt = :token";
		TypedQuery<EmailLink> typedQuery = getEntityManager().createQuery(query, EmailLink.class);
		typedQuery.setParameter("userId", userId);
		typedQuery.setParameter("token", token);
		return typedQuery.getSingleResult();
	}

	//Getters and setters
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
