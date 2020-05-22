package com.pdz.cartaocredito.service;

import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.pdz.cartaocredito.entity.Loja;
import com.pdz.cartaocredito.entity.MaquinaCartaoCredito;
import com.pdz.cartaocredito.exception.ObjectNotFoundException;
import com.pdz.cartaocredito.repository.MaquinaCartaoCreditoRepository;

@RunWith(MockitoJUnitRunner.Silent.class) 
public class MaquinaCartaoCreditoServiceTest {
	

	@InjectMocks
	private MaquinaCartaoCreditoService maquinaService;
	
	@Mock
	private MaquinaCartaoCreditoRepository maquinaRepository;
	
	private MaquinaCartaoCredito maquinaCartaoCredito;
	
	/**
	 * Inicializa elementos comuns entre os testes
	 * 
	 */
	@Before
	public void setUp() {
		Loja loja = new Loja();
		loja.setId(1);
		maquinaCartaoCredito = new MaquinaCartaoCredito(null, "2kj5676", "123", loja);
	}
	
	@Test
	public void testBuscarTodosTest() {
	
		assertNotNull(maquinaService.buscarTodos());
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void buscarMaquinaInexistenteTest() {
	
		Mockito.when(maquinaRepository.findAllById(Mockito.any())).thenReturn(null);
		
		maquinaService.buscarMaquina(123);
	}
	
	@Test
	public void buscarMaquinaTest() {
	
		Mockito.when(maquinaRepository.findById(Mockito.any())).thenReturn(Optional.of(maquinaCartaoCredito));
		
		MaquinaCartaoCredito maq = maquinaService.buscarMaquina(123);
		
		assertNotNull(maq);
	}
}
