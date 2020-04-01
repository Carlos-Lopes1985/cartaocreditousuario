package com.pdz.cartaocredito.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pdz.cartaocredito.entity.CartaoCredito;
import com.pdz.cartaocredito.entity.dto.CartaoCreditoDTO;
import com.pdz.cartaocredito.service.CartaoCreditoService;


@RestController
@RequestMapping(value="/cartaocredito")
public class CartaoCreditoResource {
	
	@Autowired
	private CartaoCreditoService cartaoCreditoService;
	
	@RequestMapping(value="/{numero}/{valor}", method = RequestMethod.GET)
	public ResponseEntity<CartaoCreditoDTO>findById(@PathVariable String numero, @PathVariable Double valor) 
			throws Exception{
		
		CartaoCreditoDTO obj = cartaoCreditoService.verificaSeCartaoTemLimite(numero,valor);
		
		return ResponseEntity.ok().body(obj);
		}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CartaoCredito>> findAll()throws Exception{
		
		return ResponseEntity.ok().body(cartaoCreditoService.buscarTodos());
	}
	
}

	

