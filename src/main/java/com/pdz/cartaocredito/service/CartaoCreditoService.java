package com.pdz.cartaocredito.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdz.cartaocredito.entity.CartaoCredito;
import com.pdz.cartaocredito.repository.CartaoCreditoRepository;

@Service
public class CartaoCreditoService {
	
	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;
	
	public CartaoCreditoDTO retornaSeExisteLimite(String numero) {
		
		CartaoCredito  cc = cartaoCreditoRepository.findByNumeroCartao(numero);
		
		return null;
	}
}
