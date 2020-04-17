package com.pdz.cartaocredito.entity.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.pdz.cartaocredito.service.validations.LojaInsert;

@LojaInsert
public class LojaNewDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message="Preenchimento nome obrigatório")
	private String nome;
	
	private String cnpj;
	
	public LojaNewDTO() {
		super();
	}
	public LojaNewDTO(String nome, String cnpj) {
		super();
		this.nome = nome;
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
}
