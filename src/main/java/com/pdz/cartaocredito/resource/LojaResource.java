package com.pdz.cartaocredito.resource;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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

import com.pdz.cartaocredito.entity.Loja;
import com.pdz.cartaocredito.entity.dto.ArquivoDTO;
import com.pdz.cartaocredito.entity.dto.LojaNovoDTO;
import com.pdz.cartaocredito.entity.dto.ResponsavelSalvarArquivoDTO;
import com.pdz.cartaocredito.service.LojaService;

import jxl.read.biff.BiffException;

@RestController
@RequestMapping(value="/lojas")
public class LojaResource {
	
	@Autowired
	private LojaService lojaService;
	
	private static final Logger log = LoggerFactory.getLogger(LojaResource.class);
	
	/**
	 * Busca todas as lojas cadastradas
	 * 
	 * @return
	 * @throws Exception
	 */
	//TODO: Descomentar quando o desenvolvimento for finalizado
	//@PreAuthorize("hasAnyRole('ADMIN')") 
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Loja>> findAll()throws Exception{
		
		log.info("Buscando todas as lojas cadastradas, findAll(){}.");
		
		return ResponseEntity.ok().body(lojaService.buscarTodos());
	}
	
	/**
	 * Busca loja por id 
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Loja> findById(@PathVariable Integer id)throws Exception{
		
		log.info("Buscando todas as lojas cadastradas por id, findById(){}.");
		
		return ResponseEntity.ok().body(lojaService.buscarLoja(id));
	}
	
	/**
	 * Responsável por inserir uma loja
	 * 
	 * @param objDto
	 * @return
	 */
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody LojaNovoDTO objDto){
		
		log.info("Inserindo lojas, insert(){}.", objDto.getClass());
		
		Loja obj = lojaService.fromDto(objDto);
		
		obj = lojaService.salvar(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * Importa uma lista de lojas para o sistema
	 * 
	 * @param arquivo
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	@RequestMapping(value="/importar",method = RequestMethod.POST)
	public ResponseEntity<ResponsavelSalvarArquivoDTO> importArquivoExcelParaBanco(@Valid @RequestBody ArquivoDTO arquivo) 
			throws BiffException,IOException{
		
		log.info("Importando lojas para o banco de dados, importArquivoExcelParaBanco(){}.", arquivo.getClass());
		
		ResponsavelSalvarArquivoDTO resp = lojaService.importExcelParaBanco(arquivo.getCaminho());
		
		return ResponseEntity.ok().body(resp);
	}
}
