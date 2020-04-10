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
	
	public boolean verificaSeCartaoTemLimite(String numero, Double valor) {
	
		CartaoCredito cc  = buscaCartaoPorNumero(numero);
		Boolean       bOk = false;
		
		if(valor < cc.getLimiteDisponivelAtual()) 
			bOk = true;
		
		return bOk;
	}
	
	public boolean verificaSeNumeroCartaoInvalidoExiste(String numero) {
		
		Boolean bOk = true;
		
		if(numero.length() != 16) {
			bOk = false;
		}else {
			try {
				 buscaCartaoPorNumero(numero);
			} catch (Exception e) {
				bOk = false;
			}
		}
		return bOk;
	}
	
	public boolean verificaNumeroCartaoCodSeguranca(String numero, String cod) {
		
		Boolean bOk = true;
		
		try {
			cartaoCreditoRepository.findByNumeroCartaoAndCodSeguranca(numero, cod);
		} catch (Exception e) {
			bOk=false;
		}
		
		return bOk;
	}
	
}
