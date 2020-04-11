package com.pdz.cartaocredito.entity.dto;

public class LojaNewDTO {
	
	private String nome;
	private String senha;
	private String cnpj;
	
	public LojaNewDTO() {
		super();
	}
	public LojaNewDTO(String nome, String senha, String cnpj) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
}
