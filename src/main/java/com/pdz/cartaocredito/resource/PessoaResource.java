package com.pdz.cartaocredito.resource;

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

import com.pdz.cartaocredito.entity.Cliente;
import com.pdz.cartaocredito.entity.Pessoa;
import com.pdz.cartaocredito.entity.dto.ClienteDTO;
import com.pdz.cartaocredito.service.UsuarioService;

@RestController
@RequestMapping(value="/pessoas")
public class PessoaResource {

	private static final Logger log = LoggerFactory.getLogger(PessoaResource.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	/**
	 * Responsável por retornar todas as pessoas cadastrdas
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Pessoa>> findAll()throws Exception{
		
		log.info("Buscando todas as pessoas {}.");
		
		return ResponseEntity.ok().body(usuarioService.buscarTodos());
	}
	
	/**
	 * Responsável por retornar uma pessoa buscada 
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pessoa> findById(@PathVariable Integer id)throws Exception{
		
		log.info("Buscando Pessoas cadastradas, por ID:", id);
		
		return ResponseEntity.ok().body(usuarioService.buscarUsuario(id));
	}
	
	/**
	 * Responsável por inserir pessoas na base de dados e seus cartões de crédito
	 * 
	 * @param objDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO objDto) throws Exception{
		
		log.info("Inserindo pessoas com cartão:", objDto.getIdUsuario());
		
		Cliente obj = usuarioService.fromDto(objDto);
		
		obj = usuarioService.salvar(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getIdUsuario()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
