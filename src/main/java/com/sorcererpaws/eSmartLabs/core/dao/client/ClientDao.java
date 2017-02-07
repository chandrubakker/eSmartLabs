package com.sorcererpaws.eSmartLabs.core.dao.client;

import java.util.List;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Client;

public interface ClientDao {

	public Client addClient(Client client);
	public Client updateClient(Client client);
	public Client getClient(Long clientId);
	public Client clientByUser(Long userId);
	public boolean deleteClient(Long clientId);
	public List<Client> clients();
}
