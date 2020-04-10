package com.pdz.cartaocredito.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdz.cartaocredito.entity.Loja;
import com.pdz.cartaocredito.repository.LojaRepository;

@Service
public class LojaService {
	
	@Autowired
	private LojaRepository lojaRepository;
	
	public Loja buscarLoja(Integer id) {
		return lojaRepository.findById(id).get();
	}
}
