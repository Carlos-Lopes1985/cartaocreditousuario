package com.pdz.cartaocredito.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer   idUsuario;
	private String    nome;
	private LocalDate dataNascimento;
	private String    cpf;
	private String    senha;
	@Column(unique=true)
	private String    email;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="TELEFONES")
	private Set<String> telefones = new HashSet<>();
	
	@OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL)
	private Set<CartaoCredito>cartoes = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL)
	private List<Compra>compras = new ArrayList<Compra>();

	public Usuario() {
		super();
	}

	public Usuario(Integer idUsuario, String nome, LocalDate dataNascimento, String cpf, String senha, String email) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.senha = senha;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", cpf="
				+ cpf + ", getClass()=" + getClass() + "Senha="+ senha + ", toString()=" + super.toString()
				+ "]";
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

	public Set<CartaoCredito> getCartoes() {
		return cartoes;
	}

	public void setCartoes(Set<CartaoCredito> cartoes) {
		this.cartoes = cartoes;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}
}
