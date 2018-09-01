/**
 * 
 */
package com.example.contact.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.contact.domain.Contact;

/**
 * Represent DAO Layer of {@link Contact}
 * 
 * @author Salman
 *
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
