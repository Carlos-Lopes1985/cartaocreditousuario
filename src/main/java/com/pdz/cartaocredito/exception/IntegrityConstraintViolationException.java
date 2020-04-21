package com.pdz.cartaocredito.exception;

public class IntegrityConstraintViolationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Sobre carga de construtores 
	public IntegrityConstraintViolationException(String msg) {
		super(msg);
	}
	
	public IntegrityConstraintViolationException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
