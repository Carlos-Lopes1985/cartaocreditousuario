package com.pdz.cartaocredito.resource;

import java.util.List;

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
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<MaquinaCartaoCredito>> buscarTodos()throws Exception{
		
		return ResponseEntity.ok().body(maquinaService.buscarTodos());
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<MaquinaCartaoCredito> buscarUm(@PathVariable Integer id)throws Exception{
		
		return ResponseEntity.ok().body(maquinaService.buscarMaquina(id));
	}
	
//	@RequestMapping(method = RequestMethod.POST)
//	public ResponseEntity<Void> insert(@Valid @RequestBody UsuarioDTO objDto) throws Exception{
//		
//		Usuario obj = maquinaService.fromDto(objDto);
//		
//		obj = maquinaService.salvar(obj);
//		
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//				.path("/{id}").buildAndExpand(obj.getIdUsuario()).toUri();
//		
//		return ResponseEntity.created(uri).build();
//	}
}
