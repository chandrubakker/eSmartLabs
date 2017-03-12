package com.sorcererpaws.eSmartLabs.core.service.global;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import com.sorcererpaws.eSmartLabs.core.entity.password.EmailLink;
import com.sorcererpaws.eSmartLabs.core.entity.user.User;

public interface GlobalService {

	public boolean isAdminPresent();
	//public String getOneTimeRandomToken();
	public EmailLink prepareEmailLink(User user, HttpServletRequest request, int linkFor);
	public boolean isLinkExpired(Date created);
	public Date getLinkExpiredDate(Date created);
}
