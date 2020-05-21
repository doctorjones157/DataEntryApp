package com.prod.exception;

public class UserAlreadyExistException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3154164645916290732L;
	public UserAlreadyExistException() {
        super();
    }
	public UserAlreadyExistException(String message) {
        super(message);
    }
}
