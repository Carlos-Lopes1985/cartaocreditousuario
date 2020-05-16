package com.pdz.cartaocredito.entity.dto;

public class CompraExportaResponseDTO {
	
	private String  nome;
	private String  cpf;
	private Double  valor;
	private Integer qtdeParcela;
	
	public CompraExportaResponseDTO() {
		super();
	}
	
	public CompraExportaResponseDTO(String nome, String cpf, Double valor, Integer qtdeParcela) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.valor = valor;
		this.qtdeParcela = qtdeParcela;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Integer getQtdeParcela() {
		return qtdeParcela;
	}
	public void setQtdeParcela(Integer qtdeParcela) {
		this.qtdeParcela = qtdeParcela;
	}
	
	
	
	
}
