package com.revature.repo;

public class InsufficientFundsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientFundsException(String errorMessage) {
		super(errorMessage);
	}

}
