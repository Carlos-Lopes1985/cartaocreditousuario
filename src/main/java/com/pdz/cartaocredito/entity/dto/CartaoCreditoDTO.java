package com.pdz.cartaocredito.entity.dto;

public class CartaoCreditoDTO {
	
	private Boolean limite;
	private Integer id;
	private String bandeira;
	private String numeroCartao;
	
	public CartaoCreditoDTO() {
		super();
	}

	public CartaoCreditoDTO(Boolean limite, Integer id, String bandeira, String numeroCartao) {
		super();
		this.limite = limite;
		this.id = id;
		this.bandeira = bandeira;
		this.numeroCartao = numeroCartao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public Boolean getLimite() {
		return limite;
	}

	public void setLimite(Boolean limite) {
		this.limite = limite;
	}
	
	
}
