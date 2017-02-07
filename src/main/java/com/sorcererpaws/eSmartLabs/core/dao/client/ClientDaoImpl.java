package com.sorcererpaws.eSmartLabs.core.dao.client;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Client;

@Repository
public class ClientDaoImpl implements ClientDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Client addClient(Client client) {
		
		getEntityManager().persist(client);
		return client;
	}

	@Override
	public Client updateClient(Client client) {
		return (Client) getEntityManager().merge(client);
	}

	@Override
	public Client getClient(Long clientId) {
		return (Client) getEntityManager().find(Client.class, clientId);
	}

	@Override
	public Client clientByUser(Long userId) {
		Query query = getEntityManager().createQuery("FROM Client C WHERE C.user.id = :userId");
		query.setParameter("userId", userId);
		
		return (Client) query.getSingleResult();
	}

	@Override
	public boolean deleteClient(Long clientId) {
		return (getEntityManager()
				.createQuery("DELETE FROM Client C WHERE C.id = :clientId")
				.setParameter("clientId", clientId)
				.executeUpdate() > 0 ? true : false);
	}

	@Override
	public List<Client> clients() {
		
		return getEntityManager()
				.createQuery("FROM Client C", Client.class).getResultList();
	}

	//Getters and setters
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
