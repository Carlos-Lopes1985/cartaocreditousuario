package com.pdz.cartaocredito.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdz.cartaocredito.entity.Loja;
import com.pdz.cartaocredito.entity.dto.LojaNovoDTO;
import com.pdz.cartaocredito.exception.ObjectNotFoundException;
import com.pdz.cartaocredito.repository.LojaRepository;

@Service
public class LojaService {
	
	@Autowired
	private LojaRepository lojaRepository;
	
	public List<Loja>buscarTodos(){
		return lojaRepository.findAll();
	}
	
	public Loja buscarLoja(Integer id) {
		
		Loja lojaObj = lojaRepository.findById(id).get();
		
		if(lojaObj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " +id+ "Tipo: " +Loja.class);
		}
		
		return lojaObj;
	}
	
	public Loja salvar(Loja loja) {
		return lojaRepository.save(loja);
	}
	
	public Loja fromDto(@Valid LojaNovoDTO objDto) {
		
		return new Loja(null, objDto.getNome(),objDto.getCnpj());
	}
}
