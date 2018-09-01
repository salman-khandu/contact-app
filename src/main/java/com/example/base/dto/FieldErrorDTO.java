/**
 * 
 */
package com.example.base.dto;

/**
 * @author Salman
 *
 */
public class FieldErrorDTO {

	private String fieldName;
	private String fieldError;

	public FieldErrorDTO(String fieldName, String fieldError) {
		super();
		this.fieldName = fieldName;
		this.fieldError = fieldError;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getFieldError() {
		return fieldError;
	}

}
