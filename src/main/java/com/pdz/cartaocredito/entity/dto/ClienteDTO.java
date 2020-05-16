package com.pdz.cartaocredito.entity.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@SuppressWarnings("deprecation")
public class ClienteDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer   idUsuario;
	
	@NotBlank(message="Preenchimento de nome é obrigatório")
	@Length(min=5, max=120, message="O tamanho deve estar entre 5 e 120 caracteres")
	private String    nome;
	
	@NotNull(message="Preenchimento data nascimento obrigatório")
	private LocalDate dataNascimento;
	
	@CPF
	private String    cpf;
	
	@NotBlank(message="Preenchimento senha obrigatório")
	private String    senha;
	
	@SuppressWarnings("deprecation")
	@NotBlank(message="Preenchimento email obrigatório")
	@Email(message="Email inválido")
	private String    email;
	
	@NotBlank(message="Preenchimento bandeira cartão de crédito obrigatório")
	private String bandeira;
	
	@NotBlank(message="Preenchimento numero do cartão obrigatório")
	private String numeroCartao;
	
	@NotBlank(message="Preenchimento código de segurança obrigatório")
	private String codSeguranca;
	private Double limiteDisponivelTotal;
	private Double limiteDisponivelAtual;
	private Double limiteDisponivelParaSaque;
	
	private LocalDate dataValidade;
	
	@NotNull(message="Preenchimento vencimento fatura obrigatório")
	private LocalDate vencimentoFatura;
	
	public ClienteDTO() {
		super();
	}

	public ClienteDTO(Integer idUsuario, String nome, LocalDate dataNascimento, String cpf, String senha, String email,
			String bandeira, String numeroCartao, String codSeguranca, Double limiteDisponivelTotal,
			Double limiteDisponivelAtual, Double limiteDisponivelParaSaque, LocalDate dataValidade,
			LocalDate vencimentoFatura) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.senha = senha;
		this.email = email;
		this.bandeira = bandeira;
		this.numeroCartao = numeroCartao;
		this.codSeguranca = codSeguranca;
		this.limiteDisponivelTotal = limiteDisponivelTotal;
		this.limiteDisponivelAtual = limiteDisponivelAtual;
		this.limiteDisponivelParaSaque = limiteDisponivelParaSaque;
		this.dataValidade = dataValidade;
		this.vencimentoFatura = vencimentoFatura;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
