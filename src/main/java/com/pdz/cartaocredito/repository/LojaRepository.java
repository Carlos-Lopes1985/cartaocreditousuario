package com.pdz.cartaocredito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pdz.cartaocredito.entity.Loja;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Integer> {

	Loja findByCnpj(String cnpj);
	
}
