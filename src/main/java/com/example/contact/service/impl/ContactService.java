/**
 * 
 */
package com.example.contact.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.contact.api.ContactController;
import com.example.contact.component.ContactBuilder;
import com.example.contact.domain.Contact;
import com.example.contact.dto.ContactDTO;
import com.example.contact.exception.ContactNotFoundException;
import com.example.contact.repository.ContactRepository;
import com.example.contact.service.IContactService;

/**
 * Represent service layer of {@link Contact} domain
 * 
 * @author salman
 *
 */
@Service
public class ContactService implements IContactService {

	private static Logger LOGGER = LoggerFactory.getLogger(ContactController.class);

	private ContactRepository contactRepository;

	private ApplicationContext applicationContext;

	public ContactService(ContactRepository contactRepository, ApplicationContext applicationContext) {
		this.contactRepository = contactRepository;
		this.applicationContext = applicationContext;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = true)
	@Override
	public List<ContactDTO> getAllContact() {
		LOGGER.debug("Start of getting all contact");
		return this.contactRepository.findAll().stream().map(contact -> convertToDTO(contact))
				.collect(Collectors.toList());
	}

	/**
	 * convert model to dto.
	 * 
	 * @param contact
	 * @return
	 */
	private ContactDTO convertToDTO(Contact contact) {
		ContactDTO contactDTO = new ContactDTO();
		BeanUtils.copyProperties(contact, contactDTO);
		return contactDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public void createContact(ContactDTO contactDTO) {
		LOGGER.debug("Start of create new contact with email:{}", contactDTO.getEmail());
		ContactBuilder contactBuilder = this.applicationContext.getBean(ContactBuilder.class);
		Contact contact = contactBuilder.withFirstName(contactDTO.getFirstName()).withLastName(contactDTO.getLastName())
				.withTitle(contactDTO.getTitle()).withPhone(contactDTO.getPhone())
				.withCompanyName(contactDTO.getCompanyName()).withEmail(contactDTO.getEmail()).build();
		this.contactRepository.save(contact);
		LOGGER.debug("Completed of create new contact with email:{}", contactDTO.getEmail());

	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public void updateContact(Long id, ContactDTO contactDTO) throws ContactNotFoundException {
		LOGGER.debug("Start of update existing contact with id:{}", id);
		checkContactByIdAndThrowException(id);
		ContactBuilder contactBuilder = this.applicationContext.getBean(ContactBuilder.class);
		Contact contact = contactBuilder.withId(id).withFirstName(contactDTO.getFirstName())
				.withLastName(contactDTO.getLastName()).withTitle(contactDTO.getTitle())
				.withPhone(contactDTO.getPhone()).withCompanyName(contactDTO.getCompanyName())
				.withEmail(contactDTO.getEmail()).build();
		this.contactRepository.save(contact);
		LOGGER.debug("Completed of update existing contact with id:{}", id);

	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public void deleteContact(Long contactId) throws ContactNotFoundException {
		LOGGER.debug("Start of delete contact with id:{}", contactId);
		checkContactByIdAndThrowException(contactId);
		this.contactRepository.delete(contactId);
		LOGGER.debug("Completed of delete contact with id:{}", contactId);

	}

	/**
	 * Check Contact with id and if not exist throw exception.
	 * 
	 * @param id
	 * @throws ContactNotFoundException
	 */
	private void checkContactByIdAndThrowException(Long id) throws ContactNotFoundException {
		if (this.contactRepository.findOne(id) == null) {
			LOGGER.debug("Contact with id:{} not exist", id);
			throw new ContactNotFoundException("Contact with id =" + id + " not exist");
		}
	}

}
