package com.sorcererpaws.eSmartLabs.core.service.contact;

import java.util.List;

import com.sorcererpaws.eSmartLabs.core.entity.common.Contact;

public interface ContactService {

	public Contact addContact(Contact contact);
	public Contact getContact(Long contactId);
	public boolean deleteContact(Long contactId);
	public List<Contact> contacts();
}
