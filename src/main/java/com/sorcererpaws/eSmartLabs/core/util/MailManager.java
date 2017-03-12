package com.sorcererpaws.eSmartLabs.core.util;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.sorcererpaws.eSmartLabs.core.entity.lab.Client;
import com.sorcererpaws.eSmartLabs.core.entity.password.EmailLink;
import com.sorcererpaws.eSmartLabs.core.entity.user.User;

@SuppressWarnings("deprecation")
@Component
public class MailManager {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MailManager.class);
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	/**
	 * Used to send registration email
	 * @param user
	 * @return
	 */
    public boolean sendRegistrationMail(final User user, final EmailLink emailLink) {
    	boolean mailSent = false;
    	if(user != null){
    		LOGGER.info("Preparing registration email...");
    		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
				@Override
				@SuppressWarnings("unchecked")
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
					messageHelper.setSubject("eSmartLabs - Confirm Your Email!");
					messageHelper.setFrom("noreply@sorcererpaws.com");
					messageHelper.setTo(user.getEmail());
					@SuppressWarnings("rawtypes")
					Map model = new HashMap();
					model.put("user", user);
					model.put("emailLink", emailLink);
					
					String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "confirm.vm", "UTF-8", model);
					messageHelper.setText(body, true);
				}
			};
			
			try {
				this.javaMailSender.send(messagePreparator);
				mailSent = true;
				LOGGER.info("Mail sent...");
			} catch (MailException e) {
				LOGGER.info("Mail sending failed..."+ e.getMessage());
				e.printStackTrace();
			}
    	}
    	return mailSent;
    }
    
    /**
	 * Used to send registration email
	 * @param user
	 * @return
	 */
    public boolean sendLabRegistrationMail(final Client client, final EmailLink emailLink) {
    	boolean mailSent = false;
    	if(client != null){
    		LOGGER.info("Preparing lab registration email...");
    		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
				@Override
				@SuppressWarnings("unchecked")
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
					messageHelper.setSubject("eSmartLabs - Confirm Your Lab Registration.");
					messageHelper.setFrom("noreply@sorcererpaws.com");
					messageHelper.setTo(client.getUser().getEmail());
					@SuppressWarnings("rawtypes")
					Map model = new HashMap();
					model.put("client", client);
					model.put("emailLink", emailLink);
					
					String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "confirmLab.vm", "UTF-8", model);
					messageHelper.setText(body, true);
				}
			};
			
			try {
				this.javaMailSender.send(messagePreparator);
				
				mailSent = true;
				LOGGER.info("Lab registration mail sent...");
			} catch (MailException e) {
				
				LOGGER.info("Lab registration mail sending failed..."+ e.getMessage());
				e.printStackTrace();
			}
    	}
    	return mailSent;
    }
    
    public boolean sendResetPasswordLinkMail(final User user, final EmailLink emailLink) {
    	boolean mailSent = false;
    	if(user != null){
    		LOGGER.info("Preparing reset password email...");
    		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
				@Override
				@SuppressWarnings("unchecked")
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
					messageHelper.setSubject("eSmartLab - Reset Your Password");
					messageHelper.setFrom("noreply@eSmartLab.com");
					messageHelper.setTo(user.getEmail());
					@SuppressWarnings("rawtypes")
					Map model = new HashMap();
					model.put("user", user);
					model.put("emailLink", emailLink);
					
					String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "changepassword.vm", "UTF-8", model);
					messageHelper.setText(body, true);
				}
			};
			try {
				this.javaMailSender.send(messagePreparator);
				mailSent = true;
				LOGGER.info("Mail sent...");
			} catch (MailException e) {
				LOGGER.info("Mail sending failed..."+ e.getMessage());
				e.printStackTrace();
			}
    	}
    	return mailSent;
    }
}