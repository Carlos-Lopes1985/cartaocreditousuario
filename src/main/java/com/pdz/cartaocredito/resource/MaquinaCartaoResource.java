package com.pdz.cartaocredito.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pdz.cartaocredito.entity.MaquinaCartaoCredito;
import com.pdz.cartaocredito.service.MaquinaCartaoCreditoService;

@RestController
@RequestMapping(value="maquinas")
public class MaquinaCartaoResource {

	@Autowired
	private MaquinaCartaoCreditoService maquinaService;
	
	private static final Logger log = LoggerFactory.getLogger(MaquinaCartaoResource.class);
	
	/**
	 * Efetua a busca de todas as maquinas cadastradas
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<MaquinaCartaoCredito>> buscarTodos()throws Exception{
		
		log.info("Buscando todas as maquinas cadastradas");
		
		return ResponseEntity.ok().body(maquinaService.buscarTodos());
	}
	
	/**
	 * Efetua a busca por id de uma maquina
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<MaquinaCartaoCredito> buscarUm(@PathVariable Integer id)throws Exception{
		
		log.info("Buscando todas as maquinas cadastradas por ID: ", id);
		
		return ResponseEntity.ok().body(maquinaService.buscarMaquina(id));
	}
}
