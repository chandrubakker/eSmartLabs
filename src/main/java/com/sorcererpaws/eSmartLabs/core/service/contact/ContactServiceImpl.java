package com.sorcererpaws.eSmartLabs.core.service.contact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sorcererpaws.eSmartLabs.core.dao.contact.ContactDao;
import com.sorcererpaws.eSmartLabs.core.entity.common.Contact;

@Service
@Transactional(readOnly = true)
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDao contactDao;

	@Override
	@Transactional(readOnly = false)
	public Contact addContact(Contact contact) {
		return getContactDao().addContact(contact);
	}

	@Override
	public Contact getContact(Long contactId) {
		return getContactDao().getContact(contactId);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteContact(Long contactId) {
		return getContactDao().deleteContact(contactId);
	}

	@Override
	public List<Contact> contacts() {
		return getContactDao().contacts();
	}

	public ContactDao getContactDao() {
		return contactDao;
	}

	public void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}
}
