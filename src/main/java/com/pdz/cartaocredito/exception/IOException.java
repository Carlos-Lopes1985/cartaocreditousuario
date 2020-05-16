package com.pdz.cartaocredito.exception;

public class IOException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Sobre carga de construtores 
	public IOException(String msg) {
		super(msg);
	}
	
	public IOException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
