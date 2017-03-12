package com.sorcererpaws.eSmartLabs.core.dao.contact;

import java.util.List;

import com.sorcererpaws.eSmartLabs.core.entity.common.Contact;

public interface ContactDao {

	public Contact addContact(Contact contact);
	public Contact getContact(Long contactId);
	public boolean deleteContact(Long contactId);
	public List<Contact> contacts();
}
