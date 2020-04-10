package com.pdz.cartaocredito.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CartaoCredito implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String bandeira;
	private String numeroCartao;
	private String codSeguranca;
	private Double limiteDisponivelTotal;
	private Double limiteDisponivelAtual;
	private Double limiteDisponivelParaSaque;
	private LocalDate dataValidade;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@OneToOne(mappedBy = "cartaoCredito")
	private Compra compra;
	
	public CartaoCredito() {
		super();
	}

	public CartaoCredito(Integer id, String bandeira, String numeroCartao, String codSeguranca,
			Double limiteDisponivelTotal, Double limiteDisponivelAtual, Double limiteDisponivelParaSaque,LocalDate dataValidade,
			Usuario usuario) {
		super();
		this.id = id;
		this.bandeira = bandeira;
		this.numeroCartao = numeroCartao;
		this.codSeguranca = codSeguranca;
		this.limiteDisponivelTotal = limiteDisponivelTotal;
		this.limiteDisponivelAtual = limiteDisponivelAtual;
		this.limiteDisponivelParaSaque = limiteDisponivelParaSaque;
		this.dataValidade = dataValidade;
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "CartaoCredito [id=" + id + ", bandeira=" + bandeira + ", numeroCartao=" + numeroCartao
				+ ", codSeguranca=" + codSeguranca + ", limiteDisponivelTotal=" + limiteDisponivelTotal
				+ ", limiteDisponivelAtual=" + limiteDisponivelAtual + ", limiteDisponivelParaSaque="
				+ limiteDisponivelParaSaque + ", usuario=" + usuario + "]";
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

	public String getCodSeguranca() {
		return codSeguranca;
	}

	public void setCodSeguranca(String codSeguranca) {
		this.codSeguranca = codSeguranca;
	}

	public Double getLimiteDisponivelTotal() {
		return limiteDisponivelTotal;
	}

	public void setLimiteDisponivelTotal(Double limiteDisponivelTotal) {
		this.limiteDisponivelTotal = limiteDisponivelTotal;
	}

	public Double getLimiteDisponivelAtual() {
		return limiteDisponivelAtual;
	}

	public void setLimiteDisponivelAtual(Double limiteDisponivelAtual) {
		this.limiteDisponivelAtual = limiteDisponivelAtual;
	}

	public Double getLimiteDisponivelParaSaque() {
		return limiteDisponivelParaSaque;
	}

	public void setLimiteDisponivelParaSaque(Double limiteDisponivelParaSaque) {
		this.limiteDisponivelParaSaque = limiteDisponivelParaSaque;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public LocalDate getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}
	
}
