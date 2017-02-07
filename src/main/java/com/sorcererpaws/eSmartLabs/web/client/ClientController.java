package com.sorcererpaws.eSmartLabs.web.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Client;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Lab;
import com.sorcererpaws.eSmartLabs.core.entity.user.User;
import com.sorcererpaws.eSmartLabs.core.service.client.ClientService;

@Controller
public class ClientController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);
	@Autowired
	private ClientService clientService;
	
	@RequestMapping(value = "/client/register", method = RequestMethod.GET)
	public ModelAndView newClient() {
		
		Client client = new Client();
		client.setUser(new User());
		client.setLab(new Lab());
		
		LOGGER.info("returning client register page...");
		return new ModelAndView("client/register").addObject("client", client);
	}
	
	@RequestMapping(value = "/clients", method = RequestMethod.GET)
	public ModelAndView clients() {
		
		return new ModelAndView("client/display");
	}
	
	//Getters and setters
	public ClientService getClientService() {
		return clientService;
	}
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}
}
