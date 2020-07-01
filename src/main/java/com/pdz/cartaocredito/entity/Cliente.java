package com.pdz.cartaocredito.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.pdz.cartaocredito.enums.Perfil;

@Entity
@JsonTypeName("cliente")
public class Cliente extends Pessoa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
	private Set<CartaoCredito>cartoes = new HashSet<>();

	@OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
	private List<Compra>compras = new ArrayList<Compra>();
	
	
	public Cliente(Integer idUsuario, String nome, LocalDate dataNascimento, String cpf, String senha, String email) {
		super(idUsuario, nome, dataNascimento, cpf, senha, email);
		addPerfil(Perfil.CLIENTE);
	}

	public Cliente(Integer idUsuario, String nome, LocalDate dataNascimento, String cpf, String senha, String email,
			Set<CartaoCredito> cartoes, List<Compra> compras) {
		super(idUsuario, nome, dataNascimento, cpf, senha, email);
		this.cartoes = cartoes;
		this.compras = compras;
		addPerfil(Perfil.CLIENTE);
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

	public Cliente() {
		super();
	}
	
	
}
