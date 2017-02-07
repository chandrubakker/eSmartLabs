package com.sorcererpaws.eSmartLabs.core.service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sorcererpaws.eSmartLabs.core.dao.client.ClientDao;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Client;

@Service
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDao clientDao;

	@Override
	@Transactional(readOnly = false)
	public Client addClient(Client client) {
		return getClientDao().addClient(client);
	}

	@Override
	@Transactional(readOnly = false)
	public Client updateClient(Client client) {
		return getClientDao().updateClient(client);
	}

	@Override
	public Client getClient(Long clientId) {
		return getClientDao().getClient(clientId);
	}

	@Override
	public Client clientByUser(Long userId) {
		return getClientDao().clientByUser(userId);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteClient(Long clientId) {
		return getClientDao().deleteClient(clientId);
	}

	@Override
	public List<Client> clients() {
		return getClientDao().clients();
	}

	//Getters and setters
	public ClientDao getClientDao() {
		return clientDao;
	}

	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}
}
