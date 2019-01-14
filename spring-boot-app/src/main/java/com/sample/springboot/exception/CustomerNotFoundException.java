package com.sample.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author fabiano.a.rosa
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 6724675340355701589L;
	public static final String EXCEPTION_MESSAGE = "Customer not found with ID ";
	
	/**
	 * Constructor
	 * @param id
	 */
	public CustomerNotFoundException(Long id) {
		super(EXCEPTION_MESSAGE + id);
	}
}
