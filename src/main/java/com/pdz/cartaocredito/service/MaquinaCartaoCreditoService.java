package com.pdz.cartaocredito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdz.cartaocredito.entity.MaquinaCartaoCredito;
import com.pdz.cartaocredito.repository.MaquinaCartaoCreditoRepository;

@Service
public class MaquinaCartaoCreditoService {

	@Autowired
	private MaquinaCartaoCreditoRepository maquinaCartaoCreditoRepository;
	
	public List<MaquinaCartaoCredito> buscarTodos() {
		return maquinaCartaoCreditoRepository.findAll();
	}

	public MaquinaCartaoCredito buscarMaquina(Integer id) {
	
		return null;
	}

}
