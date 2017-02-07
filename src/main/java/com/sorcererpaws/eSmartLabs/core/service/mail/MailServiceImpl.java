package com.sorcererpaws.eSmartLabs.core.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sorcererpaws.eSmartLabs.core.dao.mail.MailDao;
import com.sorcererpaws.eSmartLabs.core.entity.password.EmailLink;

@Service
@Transactional(readOnly = true)
public class MailServiceImpl implements MailService {

	@Autowired
	private MailDao mailDao;

	@Override
	@Transactional(readOnly = false)
	public EmailLink addEmailLink(EmailLink emailLink) {
		return getMailDao().addEmailLink(emailLink);
	}

	@Override
	@Transactional(readOnly = false)
	public EmailLink updateEmailLink(EmailLink emailLink) {
		return getMailDao().updateEmailLink(emailLink);
	}

	@Override
	public EmailLink getEmailLink(long linkId) {
		return getMailDao().getEmailLink(linkId);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteEmailLink(long linkId) {
		return getMailDao().deleteEmailLink(linkId);
	}
	
	@Override
	public EmailLink getEmailLinkByUserAndToken(long userId, String token) {
		return getMailDao().getEmailLinkByUserAndToken(userId, token);
	}

	//Getters and setters
	public MailDao getMailDao() {
		return mailDao;
	}

	public void setMailDao(MailDao mailDao) {
		this.mailDao = mailDao;
	}
}
