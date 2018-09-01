/**
 * 
 */
package com.example.contact.component;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.contact.domain.Contact;

/**
 * Builder to build contact.
 * 
 * @author Salman
 *
 */
@Component
@Scope("prototype")
public class ContactBuilder {

	private Long id;
	private String firstName;
	private String lastName;
	private String phone;
	private String title;
	private String companyName;
	private String email;

	public ContactBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public ContactBuilder withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public ContactBuilder withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public ContactBuilder withPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public ContactBuilder withTitle(String title) {
		this.title = title;
		return this;
	}

	public ContactBuilder withCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

	public ContactBuilder withEmail(String email) {
		this.email = email;
		return this;
	}

	public Contact build() {
		return new Contact(id, firstName, lastName, phone, title, companyName, email);
	}

}
