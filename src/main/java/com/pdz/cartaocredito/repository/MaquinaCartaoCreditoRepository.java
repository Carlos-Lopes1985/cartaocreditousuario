package com.pdz.cartaocredito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pdz.cartaocredito.entity.MaquinaCartaoCredito;

@Repository
public interface MaquinaCartaoCreditoRepository extends JpaRepository<MaquinaCartaoCredito, Integer> {

}
