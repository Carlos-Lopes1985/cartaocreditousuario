package com.pdz.cartaocredito.entity.dto;

public class ArquivoDTO {
	
	private String caminho;

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public ArquivoDTO(String caminho) {
		super();
		this.caminho = caminho;
	}

	public ArquivoDTO() {
		super();
	}
}
