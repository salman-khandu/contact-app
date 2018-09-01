/**
 * 
 */
package com.example.contact.service;

import java.util.List;

import com.example.contact.dto.ContactDTO;
import com.example.contact.exception.ContactNotFoundException;

/**
 * Contact service contract.
 * 
 * @author Salman
 *
 */
public interface IContactService {

	/**
	 * Get All Contacts available in Database.
	 * 
	 * @return
	 */
	public List<ContactDTO> getAllContact();

	/**
	 * Create new contact.
	 * 
	 * @param contactDTO
	 */
	public void createContact(ContactDTO contactDTO);

	/**
	 * Update exist contact.
	 * 
	 * @param contactDTO
	 * @throws ContactNotFoundException 
	 */
	public void updateContact(Long id, ContactDTO contactDTO) throws ContactNotFoundException;

	/**
	 * Delete contact.
	 * 
	 * @param contactId
	 * @throws ContactNotFoundException 
	 */
	public void deleteContact(Long contactId) throws ContactNotFoundException;
}
