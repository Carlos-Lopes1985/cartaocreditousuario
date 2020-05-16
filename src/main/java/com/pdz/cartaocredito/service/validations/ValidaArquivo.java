package com.pdz.cartaocredito.service.validations;

import com.pdz.cartaocredito.exception.IOReaderException;

public class ValidaArquivo {

	public static void validaCaminhoArquivo(String caminho) throws IOReaderException {

		String[] nova = caminho.split("[.]");
		
		if(nova[1] != "xls") {
			throw new IOReaderException("extensão de arquivo inválido, só aceita arquivos com extensão .xls");
		}
	}

}
