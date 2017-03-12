package com.sorcererpaws.eSmartLabs.web.contact;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sorcererpaws.eSmartLabs.core.entity.common.Contact;
import com.sorcererpaws.eSmartLabs.core.entity.validation.ErrorMessage;
import com.sorcererpaws.eSmartLabs.core.entity.validation.ValidationResponse;
import com.sorcererpaws.eSmartLabs.core.service.contact.ContactService;
import com.sorcererpaws.eSmartLabs.core.validator.CompanyValidator;

@RestController
public class ContactClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContactClient.class);
	
	@Autowired
	private ContactService contactService;
	
	@RequestMapping(value = "/contact/{contactId}", method = RequestMethod.GET)
	public ResponseEntity<Contact> contactMessage(@PathVariable("contactId")Long contactId) {
		
		Contact contact = getContactService().getContact(contactId);
		if(contact == null) {
			
			return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Contact>(contact, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/contacts.json", method = RequestMethod.GET)
	public ResponseEntity<List<Contact>> contactMessages() {
		
		return new ResponseEntity<List<Contact>>(getContactService().contacts(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/contact_us", method = RequestMethod.POST)
	public ResponseEntity<ValidationResponse> createContact(@ModelAttribute("contact")Contact contact, BindingResult bindingResult) {
		
		ValidationResponse response = new ValidationResponse();
		CompanyValidator companyValidator = new CompanyValidator();
		
		try {
			
			companyValidator.validateContactForm(contact, bindingResult);
			if(bindingResult.hasErrors()) {

				List<FieldError> allErrors = bindingResult.getFieldErrors();
				List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
				for (FieldError objectError : allErrors) {
					errorMesages.add(new ErrorMessage(objectError.getField(), objectError.getDefaultMessage()));
				}

				response.setErrorMessageList(errorMesages);
				response.setStatus("FAIL");
				LOGGER.info("Error while creating/updating contact.");
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.BAD_REQUEST);
			}else {
				
				contact = getContactService().addContact(contact);
				response.setObject(contact);
				response.setStatus("SUCCESS");
				return new ResponseEntity<ValidationResponse>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			LOGGER.info("Error while creating/updating contact: "+e.getMessage());
			response.setStatus("FAIL");
			return new ResponseEntity<ValidationResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ContactService getContactService() {
		return contactService;
	}

	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}
	
}
