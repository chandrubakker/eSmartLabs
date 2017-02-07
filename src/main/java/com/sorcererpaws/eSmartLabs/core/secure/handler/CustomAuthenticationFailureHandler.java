package com.sorcererpaws.eSmartLabs.core.secure.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.sorcererpaws.eSmartLabs.core.service.global.GlobalService;
import com.sorcererpaws.eSmartLabs.core.service.user.UserService;

@Component("customAuthenticationFailureHandler")
public class CustomAuthenticationFailureHandler extends
		SimpleUrlAuthenticationFailureHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GlobalService globalService;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		logger.info("authentication failed");
		logger.info("disabling session creation");
		
		setAllowSessionCreation(false);
		logger.info("redirecting to login page");
		
		if(getGlobalService().isAdminPresent()) {
			
			response.sendRedirect(request.getContextPath()+"/login");
		}else {
			
			response.sendRedirect(request.getContextPath()+"/home");
		}
	}

	//Getters and Setters
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
}
