/**
 * 
 */
package com.example.base.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.example.base.dto.ErrorDetailsDTO;
import com.example.base.dto.FieldErrorDTO;

/**
 * Global Exception handler.
 * 
 * @author Salman
 *
 */
@ControllerAdvice
public class RestApiGlobalExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetailsDTO> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorDetailsDTO handleMethodArgumentNotValid(HttpServletRequest req, MethodArgumentNotValidException ex) {

		String errorMessage = localizeErrorMessage("error.bad.arguments");
		String errorURL = req.getRequestURL().toString();

		ErrorDetailsDTO errorInfo = new ErrorDetailsDTO(errorURL, errorMessage);

		BindingResult result = ex.getBindingResult();

		errorInfo.getFieldErrors().addAll(populateFieldErrors(result.getFieldErrors()));

		return errorInfo;
	}

	public List<FieldErrorDTO> populateFieldErrors(List<FieldError> fieldErrorList) {
		List<FieldErrorDTO> fieldErrors = new ArrayList<FieldErrorDTO>();
		StringBuilder errorMessage = new StringBuilder("");

		for (FieldError fieldError : fieldErrorList) {

			errorMessage.append(fieldError.getCode()).append(".");
			errorMessage.append(fieldError.getObjectName()).append(".");
			errorMessage.append(fieldError.getField());

			String localizedErrorMsg = localizeErrorMessage(errorMessage.toString());

			fieldErrors.add(new FieldErrorDTO(fieldError.getField(), localizedErrorMsg));
			errorMessage.delete(0, errorMessage.capacity());
		}
		return fieldErrors;
	}

	public String localizeErrorMessage(String errorCode) {
		Locale locale = LocaleContextHolder.getLocale();
		String errorMessage = this.messageSource.getMessage(errorCode, null, locale);
		return errorMessage;
	}
}
