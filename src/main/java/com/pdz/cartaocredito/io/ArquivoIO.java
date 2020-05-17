package com.pdz.cartaocredito.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.pdz.cartaocredito.entity.dto.LojaNovoDTO;
import com.pdz.cartaocredito.exception.IOReaderException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ArquivoIO {
	
	private static ArquivoIO _instance;
	
	@Value("${spring.profiles.active}")
	private String profile;
	
	private ArquivoIO() {
		// construct object . . .
	}

	// For lazy initialization
	public static synchronized ArquivoIO getInstance() {
		if (_instance==null) {
			_instance = new ArquivoIO();
		}
		return _instance;
	}
	// Remainder of class definition . . .
	
	public  List<LojaNovoDTO> importaExcel(String caminho) throws IOReaderException, BiffException {
		
		Workbook workbook = null;  
		
		try {
			workbook = Workbook.getWorkbook(new File(caminho));
		} catch (IOException e){
			throw new IOReaderException("Caminho de arquivo inválido!");
		}
	
		List<LojaNovoDTO>lojas = new ArrayList<LojaNovoDTO>();
		LojaNovoDTO      loja;
		
		Sheet sheet = workbook.getSheet(0);

		int linhas = sheet.getRows();
		
		System.out.println("Iniciando a leitura da planilha XLS:");

	    for (int i = 1; i < linhas; i++) {
	    	
	    	Cell a1 = sheet.getCell(0, i);
	    	Cell a2 = sheet.getCell(1, i);
	    	
	    	loja = new LojaNovoDTO(a1.getContents(), a2.getContents());
	    	
	    	lojas.add(loja);
	    	
	    	String as1 = a1.getContents();
	    	String as2 = a2.getContents();
	    	
	    	System.out.println("Coluna 1: " + as1);
	    	System.out.println("Coluna 2: " + as2);
		
	    } 
	    workbook.close();
		
	    return lojas;
	}
}
