package com.pdz.cartaocredito.resource;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pdz.cartaocredito.entity.Compra;
import com.pdz.cartaocredito.entity.Loja;
import com.pdz.cartaocredito.entity.dto.CompraDTO;
import com.pdz.cartaocredito.service.CompraService;

@RestController
@RequestMapping(value="/compras")
public class CompraResource {
	
	@Autowired
	private CompraService compraService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Compra>> findAll()throws Exception{
		
		return ResponseEntity.ok().body(compraService.buscarTodos());
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Compra> findById(@PathVariable Integer id)throws Exception{
		
		return ResponseEntity.ok().body(compraService.buscarCompra(id));
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Compra>efetuarCompra(CompraDTO compra)throws Exception{
		
		compra.setDataCompra(LocalDate.now()); 
			
		Compra compraObj = compraService.salvarCompras(compra);
		
		return ResponseEntity.ok().body(compraObj);
	}
}
