/**
 * 
 */
package com.example.base.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Salman
 *
 */
public class ErrorDetailsDTO {
	private Date timestamp = new Date();
	private String message;
	private String details;
	private List<FieldErrorDTO> fieldErrors = new ArrayList<FieldErrorDTO>();

	public ErrorDetailsDTO() {
	}

	public ErrorDetailsDTO(Date timestamp, String message, String details, List<FieldErrorDTO> fieldErrors) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.fieldErrors = fieldErrors;
	}
	
	public ErrorDetailsDTO(String details, String message) {
		super();
		this.message = message;
		this.details = details;
	}
	

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public List<FieldErrorDTO> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldErrorDTO> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

}
