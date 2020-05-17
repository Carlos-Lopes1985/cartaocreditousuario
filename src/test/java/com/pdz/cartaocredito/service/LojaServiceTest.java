package com.pdz.cartaocredito.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.pdz.cartaocredito.entity.Loja;
import com.pdz.cartaocredito.entity.dto.LojaNovoDTO;
import com.pdz.cartaocredito.entity.dto.ResponsavelSalvarArquivoDTO;
import com.pdz.cartaocredito.exception.IOReaderException;
import com.pdz.cartaocredito.exception.ObjectNotFoundException;
import com.pdz.cartaocredito.io.ArquivoIO;
import com.pdz.cartaocredito.repository.LojaRepository;

import jxl.read.biff.BiffException;

@RunWith(MockitoJUnitRunner.Silent.class) 
public class LojaServiceTest {

	@InjectMocks
	private LojaService lojaService;
	
	@Mock
	private LojaRepository lojaRepository;
	
	private String caminhoInexistente; 
	
	private String caminhoValido;
	
	private Loja loja;
	
	private List<Loja>lojas = new ArrayList<Loja>();
	
	private List<Loja>lojasNullo = new ArrayList<Loja>();
	
	/**
	 * Inicializa elementos comuns entre os testes
	 * 
	 */
	@Before
	public void setUp() {
		loja = new Loja(1,"Americanas","73557766000145");
		caminhoInexistente = "c:/import.xls";
		caminhoValido = "c:/lojaimport.xls";
		
		lojas.add(loja);
	}
	
	@Test(expected = IOReaderException.class)
	public void importExcelParaBancoCaminhoInexistente() throws BiffException {
		lojaService.importExcelParaBanco(caminhoInexistente);
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void importExcelCnpjInvalido() throws BiffException, IOReaderException {
		
		Mockito.when(lojaRepository.findByCnpj(Mockito.any())).thenReturn(loja);
		lojaService.importExcelParaBanco(caminhoValido);
	}
	
	@Ignore
	@Test
	public void importExcelSucesso() throws BiffException, IOReaderException {
		
		LojaNovoDTO lj = new LojaNovoDTO("Americas","73557766000145");
		List<LojaNovoDTO>ljs = new ArrayList<LojaNovoDTO>();
		
		ljs.add(lj);
		
		Mockito.when(lojaService.formataStringCaminho(caminhoValido)).thenReturn(caminhoValido);
		Mockito.when(ArquivoIO.getInstance().importaExcel("lojaimport.xls")).thenReturn(ljs);
		Mockito.when(lojaRepository.findByCnpj(Mockito.any())).thenReturn(null);
		
		ResponsavelSalvarArquivoDTO loja = lojaService.importExcelParaBanco("lojaimport.xls");
		
		assertEquals(new Integer(8), loja.getContadorImportados());
		assertEquals(2,loja.getRegistrosNaoImportados().size());
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void salvarLojaObjetoNuloTest() throws ObjectNotFoundException {
		Loja loja = null;
		lojaService.salvar(loja);
	}
	
	@Test
	public void salvarLojaSucessoTest() {
		
		Mockito.when(lojaRepository.findAll()).thenReturn(null);
		
		when(lojaService.salvar(loja)).thenReturn(new Loja());
		
	}
	
	@Test
	public void buscarTodosTest() {
		
		Mockito.when(lojaRepository.findAll()).thenReturn(lojas);
		
		when(lojaService.buscarTodos()).thenReturn(lojas);
		
		assertEquals(lojas.size(), 1);
		assertNotNull(lojas.size());
	}
	
	@Test
	public void buscarTodosNuloTest() {
		
		Mockito.when(lojaRepository.findAll()).thenReturn(lojasNullo);
		
		when(lojaService.buscarTodos()).thenReturn(lojasNullo);
		
		assertTrue(lojasNullo.isEmpty());
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void buscarPorIdNaoEncontradaTest() {

		Loja loja = lojaService.buscarLoja(1);
		
		assertNull(loja);
	}
	
	@Test
	public void buscarPorIdEncontradaTest() {
		
		Mockito.when(lojaRepository.findById(Mockito.any())).thenReturn(Optional.of(loja));
		
		Loja loja = lojaService.buscarLoja(1);
		
		assertNotNull(loja);
	}
}