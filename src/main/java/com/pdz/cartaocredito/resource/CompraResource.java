package com.pdz.cartaocredito.resource;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pdz.cartaocredito.entity.Compra;
import com.pdz.cartaocredito.entity.dto.CompraDTO;
import com.pdz.cartaocredito.service.CompraService;

@RestController
@RequestMapping(value="/compras")
public class CompraResource {
	
	@Autowired
	private CompraService compraService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Compra>efetuarCompra(CompraDTO compra)throws Exception{
		
		compra.setDataCompra(LocalDate.now());
			
		Compra compraObj = compraService.salvarCompras(compra);
		
		return ResponseEntity.ok().body(compraObj);
	}
}
