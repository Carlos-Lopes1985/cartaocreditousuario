package com.pdz.cartaocredito.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdz.cartaocredito.entity.CartaoCredito;
import com.pdz.cartaocredito.exception.ObjectNotFoundException;
import com.pdz.cartaocredito.repository.CartaoCreditoRepository;

/**
 * Responsável pelas funcionalidades de cartão de crédito
 * 
 * @author zupper
 *
 */
@Service
public class CartaoCreditoService {
	
	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;
	
	private static final Logger log = LoggerFactory.getLogger(CartaoCreditoService.class);
	
	/**
	 * Responsável por buscar um cartão pelo número
	 * 
	 * @param numero
	 * @return
	 */
	public CartaoCredito buscaCartaoPorNumero(String numero) {
		log.info("Iniciando busca de cartão por número, buscarCartaoPorNumero(){}.", numero);
		CartaoCredito  cc = cartaoCreditoRepository.findByNumeroCartao(numero);
		
		if(cc==null) {
			log.error("Erro na busca de cartão por número, buscarCartaoPorNumero(){}.", numero);
			throw new ObjectNotFoundException("Cartão de Crédito não encontrado! Id: " +numero+ "numero: " +CartaoCredito.class);
		}
		log.info("Fim da busca de cartão por número, buscarCartaoPorNumero(){}.",numero);
		return cc;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<CartaoCredito> buscarTodos() {
		log.info("Iniciando busca de todos os cartões, buscarTodos(){}.");
		return cartaoCreditoRepository.findAll();
	}
	
	/**
	 * 
	 * @param cartao
	 * @return
	 */
	public CartaoCredito salvar(CartaoCredito cartao) {
		log.info("Iniciando cadastro de Cartao de Crédito, salvar(){}.", CartaoCredito.class);
		return cartaoCreditoRepository.save(cartao);
	}
}
