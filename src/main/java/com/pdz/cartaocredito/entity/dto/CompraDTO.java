package com.pdz.cartaocredito.entity.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CompraDTO {
	
	@NotNull(message="Preenchimento data compra obrigatório")
	private LocalDate dataCompra;
	@NotBlank(message="Preenchimento valor obrigatório")
	private Double valor;
	@NotBlank(message="Preenchimento serial maquina obrigatório")
	private String serial;
	@NotBlank(message="Preenchimento numero cartão de crédito obrigatório")
	private String numeroCartao;
	@NotBlank(message="Preenchimento cod segurança cartão de crédito obrigatório")
	private String codSeguranca;
	@NotBlank(message="Preenchimento senha cartão de crédito obrigatório")
	private String senha;
	
	public CompraDTO() {
		super();
	}
	
	public CompraDTO(LocalDate dataCompra, Double valor, String serial, String numeroCartao,
			String codSeguranca,String senha) {
		super();
		this.dataCompra = dataCompra;
		this.valor = valor;
		this.serial = serial;
		this.numeroCartao = numeroCartao;
		this.codSeguranca = codSeguranca;
		this.senha = senha;
	}

	public LocalDate getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(LocalDate dataCompra) {
		this.dataCompra = dataCompra;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getSerial() {
		return serial;
	}
	public void setLoja(String serial) {
		this.serial = serial;
	}
	
	public String getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getCodSeguranca() {
		return codSeguranca;
	}

	public void setCodSeguranca(String codSeguranca) {
		this.codSeguranca = codSeguranca;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
