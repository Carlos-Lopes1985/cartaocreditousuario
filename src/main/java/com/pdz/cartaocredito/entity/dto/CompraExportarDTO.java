package com.pdz.cartaocredito.entity.dto;

import java.time.LocalDate;

public class CompraExportarDTO {
	
	 private LocalDate dataCompra;
	 private String cnpjLoja;
	
	 public CompraExportarDTO() {
		super();
	}
	
	 public CompraExportarDTO(LocalDate dataCompra, String cnpjLoja) {
		super();
		this.dataCompra = dataCompra;
		this.cnpjLoja = cnpjLoja;
	}
	
	public LocalDate getDataCompra() {
		return dataCompra;
	}
	
	public void setDataCompra(LocalDate dataCompra) {
		this.dataCompra = dataCompra;
	}
	
	public String getCnpjLoja() {
		return cnpjLoja;
	}
	
	public void setCnpjLoja(String cnpjLoja) {
		this.cnpjLoja = cnpjLoja;
	}
	 
	 
}
