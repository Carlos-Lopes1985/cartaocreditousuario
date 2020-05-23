package com.pdz.cartaocredito.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pdz.cartaocredito.entity.Compra;
import com.pdz.cartaocredito.entity.MaquinaCartaoCredito;
import com.pdz.cartaocredito.repository.CompraRepository;

@RunWith(MockitoJUnitRunner.Silent.class) 
public class DBServiceTest {
	
	@InjectMocks
	private DBService dBService;
	
	@Mock
	private CompraRepository compraRepository;
	
	@Mock
	private BCryptPasswordEncoder pe;
	
	private Compra compra;
	
	private List<Compra>compras = new ArrayList<Compra>();
	/**
	 * Inicializa elementos comuns entre os testes
	 * @throws Exception 
	 * 
	 */
	@Before
	public void setUp() throws Exception {
		 compra = new Compra();
		compra.setId(1);
		
		compras.add(compra);
	}

	
	@Test(expected = NullPointerException.class)
	public void dBServiceTest() {
		
		Mockito.when(compraRepository.saveAll(Mockito.any())).thenReturn(null);
		
		dBService.instanciateTestDatabase();
	}
}
