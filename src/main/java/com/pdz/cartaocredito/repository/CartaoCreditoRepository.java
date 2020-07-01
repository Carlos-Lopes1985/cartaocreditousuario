package com.pdz.cartaocredito.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pdz.cartaocredito.entity.CartaoCredito;

@Repository
public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Integer>{
	
	CartaoCredito findByNumeroCartao(String numero);
	
	CartaoCredito findByNumeroCartaoAndCodSeguranca(String numero, String codSeguranca);
	
	CartaoCredito findByVencimentoFatura(LocalDate dataVencimento);
}
