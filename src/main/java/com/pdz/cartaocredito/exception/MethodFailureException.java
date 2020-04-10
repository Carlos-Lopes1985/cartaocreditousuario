package com.pdz.cartaocredito.exception;

public class MethodFailureException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Sobre carga de construtores 
	public MethodFailureException(String msg) {
		super(msg);
	}
	
	public MethodFailureException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
