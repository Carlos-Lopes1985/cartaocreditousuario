package com.pdz.cartaocredito.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.pdz.cartaocredito.entity.CartaoCredito;
import com.pdz.cartaocredito.entity.Cliente;
import com.pdz.cartaocredito.exception.ObjectNotFoundException;
import com.pdz.cartaocredito.repository.CartaoCreditoRepository;

@RunWith(MockitoJUnitRunner.Silent.class) 
public class CartaoCreditoServiceTest {
	
	private static final String NUMERO_CARTAO_VALIDO = "11112222333344445555";

	@InjectMocks
	private CartaoCreditoService cartaoCreditoService;
	
	@Mock
	private CartaoCreditoRepository cartaoCreditoRepository;
	
	private CartaoCredito cc;
	/**
	 * Inicializa elementos comuns entre os testes
	 * 
	 */
	@Before
	public void setUp() {
		Cliente c = new Cliente();
		c.setIdUsuario(1);
		cc = new CartaoCredito(1, "Master", NUMERO_CARTAO_VALIDO, "231", 200., 1000., 5000., LocalDate.now(), LocalDate.now(),c);
	}
	
	
	@Test
	public void testBuscarTodosTest() {
	
		assertNotNull(cartaoCreditoService.buscarTodos());
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void buscarNumeroCartaoInexistenteTest() {
	
		Mockito.when(cartaoCreditoRepository.findByNumeroCartao(Mockito.any())).thenReturn(null);
		
		cartaoCreditoService.buscaCartaoPorNumero("1111111112222222");
	}
	
	@Test
	public void buscarNumeroCartaoExistenteTest() {
		
		Mockito.when(cartaoCreditoRepository.findByNumeroCartao(Mockito.any())).thenReturn(cc);
		CartaoCredito cCredito = cartaoCreditoService.buscaCartaoPorNumero(NUMERO_CARTAO_VALIDO);
		
		assertEquals(NUMERO_CARTAO_VALIDO,cCredito.getNumeroCartao());
		
	}
	
	@Test
	public void salvarLojaSucessoTest() {
		
		when(cartaoCreditoService.salvar(cc)).thenReturn(new CartaoCredito());
	}
}
