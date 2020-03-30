package com.pdz.cartaocredito.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdz.cartaocredito.entity.CartaoCredito;
import com.pdz.cartaocredito.entity.dto.CartaoCreditoDTO;
import com.pdz.cartaocredito.repository.CartaoCreditoRepository;

@Service
public class CartaoCreditoService {
	
	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;
	
	public CartaoCredito buscaCartaoPorNumero(String numero) {
		
		CartaoCredito  cc = cartaoCreditoRepository.findByNumeroCartao(numero);
		
		return cc;
	}
	
	public CartaoCreditoDTO verificaSeCartaoTemLimite(String numero, Double valor) {
	
		CartaoCredito cc = buscaCartaoPorNumero(numero);
		CartaoCreditoDTO ccDto = new CartaoCreditoDTO();
		
		if(valor < cc.getLimiteDisponivelAtual()) {
			ccDto.setLimite(true);
		}else {
			ccDto.setLimite(false);
		}
			
		ccDto.setId(cc.getId());
		ccDto.setBandeira(cc.getBandeira());
		ccDto.setNumeroCartao(cc.getNumeroCartao());
		
		return ccDto;
	}
}
