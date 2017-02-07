package com.sorcererpaws.eSmartLabs.core.dao.mail;

import com.sorcererpaws.eSmartLabs.core.entity.password.EmailLink;

public interface MailDao {

	public EmailLink addEmailLink(EmailLink emailLink);
	public EmailLink updateEmailLink(EmailLink emailLink);
	public EmailLink getEmailLink(long linkId);
	public boolean deleteEmailLink(long linkId);
	public EmailLink getEmailLinkByUserAndToken(long userId, String token);
}
