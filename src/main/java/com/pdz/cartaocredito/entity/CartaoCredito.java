package com.pdz.cartaocredito.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	private LocalDate vencimentoFatura;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@JsonIgnore
	@OneToMany(mappedBy = "cartaoCredito")
	private List<Compra> compra;
	
	public CartaoCredito() {
		super();
	}

	public CartaoCredito(Integer id, String bandeira, String numeroCartao, String codSeguranca,
			Double limiteDisponivelTotal, Double limiteDisponivelAtual, Double limiteDisponivelParaSaque,LocalDate dataValidade,
			LocalDate vencimentoFatura,Cliente cliente) {
		super();
		this.id = id;
		this.bandeira = bandeira;
		this.numeroCartao = numeroCartao;
		this.codSeguranca = codSeguranca;
		this.limiteDisponivelTotal = limiteDisponivelTotal;
		this.limiteDisponivelAtual = limiteDisponivelAtual;
		this.limiteDisponivelParaSaque = limiteDisponivelParaSaque;
		this.dataValidade = dataValidade;
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "CartaoCredito [id=" + id + ", bandeira=" + bandeira + ", numeroCartao=" + numeroCartao
				+ ", codSeguranca=" + codSeguranca + ", limiteDisponivelTotal=" + limiteDisponivelTotal
				+ ", limiteDisponivelAtual=" + limiteDisponivelAtual + ", limiteDisponivelParaSaque="
				+ limiteDisponivelParaSaque + ", usuario=" + cliente + "]";
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bandeira == null) ? 0 : bandeira.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((codSeguranca == null) ? 0 : codSeguranca.hashCode());
		result = prime * result + ((compra == null) ? 0 : compra.hashCode());
		result = prime * result + ((dataValidade == null) ? 0 : dataValidade.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((limiteDisponivelAtual == null) ? 0 : limiteDisponivelAtual.hashCode());
		result = prime * result + ((limiteDisponivelParaSaque == null) ? 0 : limiteDisponivelParaSaque.hashCode());
		result = prime * result + ((limiteDisponivelTotal == null) ? 0 : limiteDisponivelTotal.hashCode());
		result = prime * result + ((numeroCartao == null) ? 0 : numeroCartao.hashCode());
		result = prime * result + ((vencimentoFatura == null) ? 0 : vencimentoFatura.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartaoCredito other = (CartaoCredito) obj;
		if (bandeira == null) {
			if (other.bandeira != null)
				return false;
		} else if (!bandeira.equals(other.bandeira))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (codSeguranca == null) {
			if (other.codSeguranca != null)
				return false;
		} else if (!codSeguranca.equals(other.codSeguranca))
			return false;
		if (compra == null) {
			if (other.compra != null)
				return false;
		} else if (!compra.equals(other.compra))
			return false;
		if (dataValidade == null) {
			if (other.dataValidade != null)
				return false;
		} else if (!dataValidade.equals(other.dataValidade))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (limiteDisponivelAtual == null) {
			if (other.limiteDisponivelAtual != null)
				return false;
		} else if (!limiteDisponivelAtual.equals(other.limiteDisponivelAtual))
			return false;
		if (limiteDisponivelParaSaque == null) {
			if (other.limiteDisponivelParaSaque != null)
				return false;
		} else if (!limiteDisponivelParaSaque.equals(other.limiteDisponivelParaSaque))
			return false;
		if (limiteDisponivelTotal == null) {
			if (other.limiteDisponivelTotal != null)
				return false;
		} else if (!limiteDisponivelTotal.equals(other.limiteDisponivelTotal))
			return false;
		if (numeroCartao == null) {
			if (other.numeroCartao != null)
				return false;
		} else if (!numeroCartao.equals(other.numeroCartao))
			return false;
		if (vencimentoFatura == null) {
			if (other.vencimentoFatura != null)
				return false;
		} else if (!vencimentoFatura.equals(other.vencimentoFatura))
			return false;
		return true;
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

	public Pessoa getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Compra> getCompra() {
		return compra;
	}

	public void setCompra(List<Compra> compra) {
		this.compra = compra;
	}

	public LocalDate getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}

	public LocalDate getVencimentoFatura() {
		return vencimentoFatura;
	}

	public void setVencimentoFatura(LocalDate vencimentoFatura) {
		this.vencimentoFatura = vencimentoFatura;
	}
}
