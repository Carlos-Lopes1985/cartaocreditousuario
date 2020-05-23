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

import com.pdz.cartaocredito.entity.Cliente;
import com.pdz.cartaocredito.entity.Pessoa;
import com.pdz.cartaocredito.entity.dto.ClienteDTO;
import com.pdz.cartaocredito.service.PessoaService;

@RestController
@RequestMapping(value="/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaService usuarioService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Pessoa>> findAll()throws Exception{
		
		return ResponseEntity.ok().body(usuarioService.buscarTodos());
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pessoa> findById(@PathVariable Integer id)throws Exception{
		
		return ResponseEntity.ok().body(usuarioService.buscarUsuario(id));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO objDto) throws Exception{
		
		Cliente obj = usuarioService.fromDto(objDto);
		
		obj = usuarioService.salvar(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getIdUsuario()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
