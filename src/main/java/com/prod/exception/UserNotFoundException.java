package com.prod.exception;

public class UserNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3154164645916290732L;
	public UserNotFoundException() {
        super();
    }
	public UserNotFoundException(String message) {
        super(message);
    }
}
