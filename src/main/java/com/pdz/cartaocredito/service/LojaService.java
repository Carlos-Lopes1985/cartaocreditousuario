package com.pdz.cartaocredito.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pdz.cartaocredito.entity.Loja;
import com.pdz.cartaocredito.entity.dto.LojaNewDTO;
import com.pdz.cartaocredito.repository.LojaRepository;

@Service
public class LojaService {
	
	@Autowired
	private LojaRepository lojaRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public List<Loja>buscarTodos(){
		return lojaRepository.findAll();
	}
	public Loja buscarLoja(Integer id) {
		return lojaRepository.findById(id).get();
	}
	
	public Loja salvar(Loja loja) {
		return lojaRepository.save(loja);
	}
	public Loja fromDto(@Valid LojaNewDTO objDto) {
		
		return new Loja(null, objDto.getNome(),pe.encode(objDto.getSenha()),objDto.getCnpj());
	}
}
