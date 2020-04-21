package com.pdz.cartaocredito.entity;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Compra implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private LocalDate dataCompra;
	private Integer status;
	private Double valor;
	private Integer qtdeParcela;
	
	@ManyToOne
	@JoinColumn(name = "id_loja")
	private Loja loja;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cartao_id")
	private CartaoCredito cartaoCredito;

	public Compra() {
		super();
	}

	public Compra(Integer id, LocalDate dataCompra, Integer status, Double valor, Integer qtdeParcela, Loja loja,
			Usuario usuario, CartaoCredito cartaoCredito) {
		super();
		this.id = id;
		this.dataCompra = dataCompra;
		this.status = status;
		this.valor = valor;
		this.qtdeParcela = qtdeParcela;
		this.loja = loja;
		this.usuario = usuario;
		this.cartaoCredito = cartaoCredito;
	}

	
	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		
		StringBuilder builder = new StringBuilder();
		builder.append("Compra [id=");
		builder.append(getId());
		builder.append(", dataCompra=");
		builder.append(getDataCompra());
		builder.append(", status=");
		builder.append(getStatus());
		builder.append(", valor=");
		builder.append(nf.format(getValor()));
		builder.append(", qtdeParcela=");
		builder.append(getQtdeParcela());
		builder.append("\n");
		builder.append(", loja=");
		builder.append(getLoja());
		builder.append("\n");
		builder.append(", usuario=");
		builder.append(getUsuario());
		builder.append("\n");
		builder.append("]");
		
		return builder.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(LocalDate dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public CartaoCredito getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(CartaoCredito cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

	public Integer getQtdeParcela() {
		return qtdeParcela;
	}

	public void setQtdeParcela(Integer qtdeParcela) {
		this.qtdeParcela = qtdeParcela;
	}
	
	
}
