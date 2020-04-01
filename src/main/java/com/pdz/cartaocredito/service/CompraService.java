package com.pdz.cartaocredito.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdz.cartaocredito.entity.Compra;
import com.pdz.cartaocredito.repository.CompraRepository;

@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;
	
	public Compra salvarCompras(Compra compra) {
		
		return compraRepository.save(compra);
	}

}
