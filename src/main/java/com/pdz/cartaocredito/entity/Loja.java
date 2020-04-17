package com.pdz.cartaocredito.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pdz.cartaocredito.enums.Perfil;

@Entity
public class Loja implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nome;
	
	private String cnpj;
	
	@JsonIgnore
	@OneToMany(mappedBy = "loja")
	private List<Compra> compra = new ArrayList<Compra>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "loja")
	private List<MaquinaCartaoCredito> maquinaCartao = new ArrayList<MaquinaCartaoCredito>();
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	public Loja() {
		addPerfil(Perfil.CLIENTE);
	}
	
	
	public Loja(Integer id, String nome, String cnpj) {
		super();
		this.id = id;
		this.nome = nome;
		//this.senha = senha;
		this.cnpj = cnpj;
		addPerfil(Perfil.CLIENTE);
	}

	public Loja(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
		addPerfil(Perfil.CLIENTE);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@JsonIgnore
	public List<Compra> getCompras() {
		return compra;
	}

	public void setCompras(List<Compra> compras) {
		this.compra = compras;
	}

	public List<MaquinaCartaoCredito> getMaquinaCartao() {
		return maquinaCartao;
	}

	public void setMaquinaCartao(List<MaquinaCartaoCredito> maquinaCartao) {
		this.maquinaCartao = maquinaCartao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	//responsável por retornar todos os perfils do usuário
	public Set<Perfil> getPerfils(){
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
		
	}
	
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}
	
}
