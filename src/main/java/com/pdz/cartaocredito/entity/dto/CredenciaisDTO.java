package com.pdz.cartaocredito.entity.dto;

import java.io.Serializable;

public class CredenciaisDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String serial;
	private String senha;

	public CredenciaisDTO() {
		super();
	}

	public CredenciaisDTO(String serial, String senha) {
		super();
		this.serial = serial;
		this.senha = senha;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
