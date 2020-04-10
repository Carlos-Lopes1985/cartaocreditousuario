package com.pdz.cartaocredito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdz.cartaocredito.entity.CartaoCredito;
import com.pdz.cartaocredito.repository.CartaoCreditoRepository;

@Service
public class CartaoCreditoService {
	
	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;
	
	public CartaoCredito buscaCartaoPorNumero(String numero) {
		
		CartaoCredito  cc = cartaoCreditoRepository.findByNumeroCartao(numero);
		
		return cc;
	}
	
	public List<CartaoCredito> buscarTodos() {
		return cartaoCreditoRepository.findAll();
	}
	
	public CartaoCredito salvar(CartaoCredito cartao) {
		return cartaoCreditoRepository.save(cartao);
	}
}
