/**
 * 
 */
package com.example.contact.exception;

/**
 * Custome exception
 * 
 * @author Salman
 *
 */
public class ContactNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContactNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public ContactNotFoundException(String message) {
		super(message);
	}
}
