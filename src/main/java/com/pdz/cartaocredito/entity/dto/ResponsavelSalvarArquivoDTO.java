package com.pdz.cartaocredito.entity.dto;

import java.util.List;

public class ResponsavelSalvarArquivoDTO {
	
	private Integer     contadorImportados;;
	private List<String>registrosNaoImportados;
	
	public ResponsavelSalvarArquivoDTO() {
		super();
	}

	public Integer getContadorImportados() {
		return contadorImportados;
	}


	public void setContadorImportados(Integer contadorImportados) {
		this.contadorImportados = contadorImportados;
	}


	public List<String> getRegistrosNaoImportados() {
		return registrosNaoImportados;
	}


	public void setRegistrosNaoImportados(List<String> registrosNaoImportados) {
		this.registrosNaoImportados = registrosNaoImportados;
	}
}
