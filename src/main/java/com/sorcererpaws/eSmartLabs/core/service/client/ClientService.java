package com.sorcererpaws.eSmartLabs.core.service.client;

import java.util.List;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Client;

public interface ClientService {

	public Client addClient(Client client);
	public Client updateClient(Client client);
	public Client getClient(Long clientId);
	public Client clientByUser(Long userId);
	public boolean deleteClient(Long clientId);
	public List<Client> clients();
}
