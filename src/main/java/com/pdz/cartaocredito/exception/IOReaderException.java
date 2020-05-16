package com.pdz.cartaocredito.exception;

public class IOReaderException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Sobre carga de construtores 
	public IOReaderException(String msg) {
		super(msg);
	}
	
	public IOReaderException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
