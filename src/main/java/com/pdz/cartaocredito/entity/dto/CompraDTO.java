package com.pdz.cartaocredito.entity.dto;

import java.time.LocalDate;

public class CompraDTO {
	
	private LocalDate dataCompra;
	private Double valor;
	private Integer loja;
	private Integer idUsuario;
	private String numeroCartao;
	private String codSeguranca;
	
	public CompraDTO() {
		super();
	}
	
	public CompraDTO(LocalDate dataCompra, Double valor, Integer loja, Integer idUsuario, String numeroCartao,
			String codSeguranca) {
		super();
		this.dataCompra = dataCompra;
		this.valor = valor;
		this.loja = loja;
		this.idUsuario = idUsuario;
		this.numeroCartao = numeroCartao;
		this.codSeguranca = codSeguranca;
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
	public Integer getLoja() {
		return loja;
	}
	public void setLoja(Integer loja) {
		this.loja = loja;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
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
}
