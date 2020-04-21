package com.pdz.cartaocredito.entity.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.pdz.cartaocredito.service.validations.LojaNovo;

@LojaNovo
public class LojaNovoDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message="Preenchimento nome obrigatório")
	@Length(min=5, max=120, message="O tamanho deve estar entre 5 e 50 caracteres")
	private String nome;
	
	@NotBlank(message="Preenchimento cnpj obrigatório")
	private String cnpj;
	
	public LojaNovoDTO() {
		super();
	}
	
	public LojaNovoDTO(String nome, String cnpj) {
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
