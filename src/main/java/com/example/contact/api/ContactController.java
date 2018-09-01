/**
 * 
 */
package com.example.contact.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.contact.domain.Contact;
import com.example.contact.dto.ContactDTO;
import com.example.contact.exception.ContactNotFoundException;
import com.example.contact.service.IContactService;

/**
 * Exposes All Rest end point related to {@link Contact} Domain.
 * 
 * @author Salman
 *
 */
@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {

	private IContactService contactService;

	public ContactController(IContactService contactService) {
		this.contactService = contactService;
	}

	/**
	 * Get all contact from DB.
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<ContactDTO>> getAllContact() {
		return new ResponseEntity<>(this.contactService.getAllContact(), HttpStatus.OK);
	}

	/**
	 * Create new contact.
	 * 
	 * @param contactDTO
	 */
	@PostMapping
	public void createContact(@RequestBody @Valid ContactDTO contactDTO) {
		this.contactService.createContact(contactDTO);
	}

	/**
	 * Update existing contact.
	 * 
	 * @param id
	 * @param contactDTO
	 * @throws ContactNotFoundException
	 */
	@PutMapping("/{id}")
	public void updateContact(@PathVariable Long id, @RequestBody @Valid ContactDTO contactDTO)
			throws ContactNotFoundException {
		this.contactService.updateContact(id, contactDTO);
	}

	/**
	 * Delete contact with id.
	 * 
	 * @param id
	 * @throws ContactNotFoundException
	 */
	@DeleteMapping("/{id}")
	public void deleteContact(@PathVariable Long id) throws ContactNotFoundException {
		this.contactService.deleteContact(id);
	}
}
