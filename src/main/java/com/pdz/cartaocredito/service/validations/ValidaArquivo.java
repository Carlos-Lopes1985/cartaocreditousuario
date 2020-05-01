package com.pdz.cartaocredito.service.validations;

import com.pdz.cartaocredito.exception.IOException;

public class ValidaArquivo {

	public static void validaCaminhoArquivo(String caminho) throws IOException {

		String[] nova = caminho.split("[.]");
		
		if(nova[1] != "xls") {
			throw new IOException("extensão de arquivo inválido, só aceita arquivos com extensão .xls");
		}
	}

}
