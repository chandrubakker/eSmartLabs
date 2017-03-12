package com.sorcererpaws.eSmartLabs.web.client;

import org.jboss.logging.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Client;
import com.sorcererpaws.eSmartLabs.core.entity.lab.Lab;
import com.sorcererpaws.eSmartLabs.core.entity.password.EmailLink;
import com.sorcererpaws.eSmartLabs.core.entity.user.User;
import com.sorcererpaws.eSmartLabs.core.service.client.ClientService;
import com.sorcererpaws.eSmartLabs.core.service.global.GlobalService;
import com.sorcererpaws.eSmartLabs.core.service.mail.MailService;
import com.sorcererpaws.eSmartLabs.core.service.user.UserService;

@Controller
public class ClientController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);
	@Autowired
	private ClientService clientService;

	@Autowired
	private UserService userService;

	@Autowired
	private GlobalService globalService;

	@Autowired
	private MailService mailService;

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

	@RequestMapping(value = "/client/confirm", method = RequestMethod.GET)
	public ModelAndView confirmLabRegistration(@Param long userId, @Param String otrt) {
		ModelAndView modelAndView = null;
		String code = null;
		if(userId > 0 && otrt != null) {
			if(!otrt.isEmpty()) {
				User user = getUserService().getUser(userId);
				if(user != null) {
					EmailLink emailLink = getMailService().getEmailLinkByUserAndToken(userId, otrt);
					if(emailLink != null) {
						if(emailLink.isActive()) {
							if(getGlobalService().isLinkExpired(emailLink.getCreated())) {
								
								//Link Expired
								emailLink.setExpired(getGlobalService().getLinkExpiredDate(emailLink.getCreated()));
								emailLink.setActive(false);
								emailLink = getMailService().updateEmailLink(emailLink);
								modelAndView = new ModelAndView("client/error");
								code = "EXP";
								modelAndView.addObject("eCode", code);
							}else {
								
								//Link Used
								if(getUserService().activateUser(userId)) {
									
									LOGGER.info("USER ACTIVATED");
								} else {
									
									LOGGER.info("USER ACTIVATION FAILED");
								}
								
								emailLink.setActive(false);
								emailLink = getMailService().updateEmailLink(emailLink);
								modelAndView = new ModelAndView("client/success");
								user.setPassword("");
								user.setConfirmPassword("");
								modelAndView.addObject("user", user);
							}	
						}else {
							//Link Inactive
							modelAndView = new ModelAndView("client/error");
							code = "USED";
							modelAndView.addObject("eCode", code);
						}
					}else {
						//Link Not Found
						modelAndView = new ModelAndView("client/error");
						code = "NF";
						modelAndView.addObject("eCode", code);
					}
				}else {
					//User NF
					modelAndView = new ModelAndView("client/error");
					code = "UNF";
					modelAndView.addObject("eCode", code);
				}
			}else {
				//Invalid Link
				modelAndView = new ModelAndView("client/error");
				code = "IL";
				modelAndView.addObject("eCode", code);
			}
		}else {
			//Invalid Link
			modelAndView = new ModelAndView("client/error");
			code = "IL";
			modelAndView.addObject("eCode", code);
		}
		return modelAndView;
	}

	//Getters and setters
	public ClientService getClientService() {
		return clientService;
	}
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public GlobalService getGlobalService() {
		return globalService;
	}

	public void setGlobalService(GlobalService globalService) {
		this.globalService = globalService;
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}
}
