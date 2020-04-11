package com.pdz.cartaocredito.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pdz.cartaocredito.entity.Loja;
import com.pdz.cartaocredito.entity.dto.LojaNewDTO;
import com.pdz.cartaocredito.service.LojaService;

@RestController
@RequestMapping(value="/lojas")
public class LojaResource {
	
	@Autowired
	private LojaService lojaService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Loja>> findAll()throws Exception{
		
		return ResponseEntity.ok().body(lojaService.buscarTodos());
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Loja> findById(@PathVariable Integer id)throws Exception{
		
		return ResponseEntity.ok().body(lojaService.buscarLoja(id));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody LojaNewDTO objDto){
		
		Loja obj = lojaService.fromDto(objDto);
		
		obj = lojaService.salvar(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}