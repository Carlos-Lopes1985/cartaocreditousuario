package com.pdz.cartaocredito.resource;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pdz.cartaocredito.entity.Compra;
import com.pdz.cartaocredito.entity.dto.CompraDTO;
import com.pdz.cartaocredito.service.CompraService;

@RestController
@RequestMapping(value="/compras")
public class CompraResource {
	
	@Autowired
	private CompraService compraService;
	
	private static final Logger log = LoggerFactory.getLogger(CompraResource.class);
	
	/**
	 * Responsável por buscar todas as compras cadastradas
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Compra>> findAll()throws Exception{
		
		log.info("Buscando todas as compras cadastradas, findAll(){}.");
		
		return ResponseEntity.ok().body(compraService.buscarTodos());
	}
	
	/**
	 * Responsável por buscar compra por id
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Compra> findById(@PathVariable Integer id)throws Exception{
		
		log.info("Buscando compra cadastrada, findById(){}.", id);
		
		return ResponseEntity.ok().body(compraService.buscarCompra(id));
	}
	
	/**
	 * Responsável por cadastrar uma nova compra no sistema
	 * 
	 * @param compra
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Compra>efetuarCompra(@RequestBody CompraDTO compra)throws Exception{
		
		log.info("Efetuando compra, efetuarCompra(){}.", CompraDTO.class);
		
		compra.setDataCompra(LocalDate.now()); 
		
		Compra compraObj = compraService.salvarCompras(compra);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(compraObj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
