package com.sorcererpaws.eSmartLabs.core.service.global;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sorcererpaws.eSmartLabs.core.entity.password.EmailLink;
import com.sorcererpaws.eSmartLabs.core.entity.user.User;
import com.sorcererpaws.eSmartLabs.core.service.user.UserService;

@Service
public class GlobalServiceImpl implements GlobalService{

	@Autowired
	private UserService userService;

	private SecureRandom random;
	
	/**
	 * Used to get the number of days between 2 dates
	 * @param t1 start date
	 * @param t2 end date
	 * @return number of days between t1 and t2
	 */
	private int daysBetween(long t1, long t2) {
		return (int) ((t2 - t1) / (1000 * 60 * 60 * 24));
	}
	
	@Override
	public boolean isAdminPresent() {
		List<User> users = getUserService().users();
		boolean thereIsAdmin = false;
		if(users != null) {
			for(User technicion : users){
				if(technicion.getRole().getId() == 1){
					thereIsAdmin = true;
				}
			}
		}
		return thereIsAdmin;
	}

	private String getOneTimeRandomToken() {
		random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}
	
	private String getRootPath(HttpServletRequest request) {
		//String protocol = request.getProtocol();
		int port = request.getServerPort();
		String host = request.getServerName();
		String rootPath = "http://"+host+":"+port+request.getContextPath();
		return rootPath;
	}
	
	/**
	 * Used to construct email link.
	 * parameter: linFor - [ 1111 - Client Confirmation, 2222 - Reset Password ]
	 */
	@Override
	public EmailLink prepareEmailLink(User user, HttpServletRequest request, int linkFor) {
		EmailLink emailLink = null;
		if(user != null) {
			
			emailLink = new EmailLink();
			emailLink.setActive(true);
			emailLink.setCreated(new Date());
			emailLink.setOtrt(getOneTimeRandomToken());
			String rootPath = getRootPath(request);
			switch (linkFor) {
			case 1111:
				
				emailLink.setLink(rootPath+"/client/confirm?userId="+user.getId()+"&otrt="+emailLink.getOtrt());
				emailLink.setType("labRegistration");
				break;
			case 2222:
				
				emailLink.setLink(rootPath+"/password/reset?userId="+user.getId()+"&otrt="+emailLink.getOtrt());
				emailLink.setType("passwordLink");
				break;		
			default:
				emailLink.setLink(rootPath);
				emailLink.setType("errorLink");
				break;
			}
			
			emailLink.setUser(user);
		}
		return emailLink;
	}
	
	@Override
	public boolean isLinkExpired(Date created) {
		Calendar start = Calendar.getInstance();
		start.setTime(created);
		Calendar end = Calendar.getInstance();
		end.setTime(new Date());
		int noOfDays = daysBetween(start.getTimeInMillis(), end.getTimeInMillis());
		if(noOfDays > 30) {
			return true;
		}
		return false;
	}
	
	@Override
	public Date getLinkExpiredDate(Date created) {
		Calendar start = Calendar.getInstance();
		start.setTime(created);
		Calendar end = Calendar.getInstance();
		end.setTime(new Date());
		int noOfDays = daysBetween(start.getTimeInMillis(), end.getTimeInMillis());
		int gap = noOfDays - 30;
		long gapMillis = (gap * 1000 * 60 * 60 * 24);
		Calendar expiredCal = Calendar.getInstance();
		expiredCal.setTimeInMillis(gapMillis);
		return expiredCal.getTime();
	}
	
	//Getters and setters
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
