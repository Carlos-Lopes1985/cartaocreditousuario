package com.pdz.cartaocredito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pdz.cartaocredito.entity.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer> {
	
	//Compra findByDataCompraAndCodSeguranca(String numero, String codSeguranca);
	
}
