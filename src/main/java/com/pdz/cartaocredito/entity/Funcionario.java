package com.pdz.cartaocredito.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.pdz.cartaocredito.enums.Perfil;

@Entity
@JsonTypeName("funcionario")
public class Funcionario extends Pessoa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String matricula;
	private LocalDate dataEntrada;
	
	
	
	public Funcionario(Integer idUsuario, String nome, LocalDate dataNascimento, String cpf, String senha, String email,
			String matricula, LocalDate dataEntrada) {
		super(idUsuario, nome, dataNascimento, cpf, senha, email);
		this.matricula = matricula;
		this.dataEntrada = dataEntrada;
		addPerfil(Perfil.ADMIN);
	}

	public Funcionario() {
		addPerfil(Perfil.ADMIN);
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
}
