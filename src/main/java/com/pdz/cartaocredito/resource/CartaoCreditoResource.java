
package com.pdz.cartaocredito.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pdz.cartaocredito.entity.CartaoCredito;
import com.pdz.cartaocredito.service.CartaoCreditoService;

@RestController
@RequestMapping(value="/cartaocredito")
public class CartaoCreditoResource {
	
	private static final Logger log = LoggerFactory.getLogger(CartaoCreditoResource.class);
	
	@Autowired
	private CartaoCreditoService cartaoCreditoService;
	
	/**
	 * Responsável por buscar todos os cartões de crédito
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CartaoCredito>> findAll()throws Exception{
		
		log.info("Buscando todos os cartões de crédito cadastrados, findAll(){}.");
		
		return ResponseEntity.ok().body(cartaoCreditoService.buscarTodos());
	}
	
}

	

