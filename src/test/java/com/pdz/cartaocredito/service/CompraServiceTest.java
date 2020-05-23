package com.pdz.cartaocredito.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doThrow;

import java.time.LocalDate;
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

import com.pdz.cartaocredito.entity.CartaoCredito;
import com.pdz.cartaocredito.entity.Cliente;
import com.pdz.cartaocredito.entity.Compra;
import com.pdz.cartaocredito.entity.Loja;
import com.pdz.cartaocredito.entity.MaquinaCartaoCredito;
import com.pdz.cartaocredito.entity.dto.CompraDTO;
import com.pdz.cartaocredito.exception.DataIntegrityException;
import com.pdz.cartaocredito.exception.ObjectNotFoundException;
import com.pdz.cartaocredito.repository.CompraRepository;
import com.pdz.cartaocredito.repository.MaquinaCartaoCreditoRepository;
import com.pdz.cartaocredito.service.email.EmailService;
import com.pdz.cartaocredito.service.validations.ValidaCartaoCredito;
import com.pdz.cartaocredito.service.validations.ValidaUsuario;

@RunWith(MockitoJUnitRunner.Silent.class) 
public class CompraServiceTest {

	@InjectMocks
	private CompraService compraService;
	
	@Mock
	private CompraRepository compraRepository;
	
	@Mock
	private ValidaCartaoCredito validaCompra;
	
	@Mock
	private ValidaUsuario validaUsuario;
	
	@Mock
	private MaquinaCartaoCreditoRepository maqRepository;
	
	@Mock
	private EmailService emailService;
	
	@Mock
	private CartaoCreditoService cartaoCreditoService;
	
	private Compra cc;
	
	private List<Compra>compras = new ArrayList<Compra>();
	
	private CompraDTO compraDto;
	
	private MaquinaCartaoCredito maq;
	
	private	CartaoCredito cartaoc ;
	
	/**
	 * Inicializa elementos comuns entre os testes
	 * @throws Exception 
	 * 
	 */
	@Before
	public void setUp() throws Exception {
		Cliente c = new Cliente();
		c.setIdUsuario(1);
		Loja l = new Loja();
		l.setId(1);

		cartaoc = new CartaoCredito();
		cartaoc.setId(1);
		cartaoc.setLimiteDisponivelAtual(200.);
		cartaoc.setCliente(c);

		cc = new Compra(1, LocalDate.now(), null, 1000., 0, l, c, cartaoc);

		compraDto = new CompraDTO(LocalDate.now(), 200., "5554446677889999", "5554446677889999", "231", "1234");

		maq = new MaquinaCartaoCredito(1, "XPTO", "123", l);
		compras.add(cc);

		Mockito.when(validaCompra.verificaInformacoesCartao(Mockito.any())).thenReturn(true);

		Mockito.when(validaUsuario.verificaSenhaUsuario(Mockito.any())).thenReturn(true);

		Mockito.when(cartaoCreditoService.buscaCartaoPorNumero(Mockito.any())).thenReturn(cartaoc);
	}

	@Test(expected = ObjectNotFoundException.class)
	public void buscarCompraInexistenteTest() {
		
		Mockito.when(compraRepository.findById(123)).thenReturn(Optional.empty());
		
		compraService.buscarCompra(123);
	}
	
	@Test
	public void buscarCompraTest() {
	
		Mockito.when(compraRepository.findById(Mockito.any())).thenReturn(Optional.of(cc));
		
		Compra compra = compraService.buscarCompra(1);
		
		assertNotNull(compra);
	}
	
	@Test
	public void salvarCompraTest() throws Exception {
		
		Mockito.when(maqRepository.findBySerial(Mockito.any())).thenReturn(maq);
		
		Mockito.when(cartaoCreditoService.buscaCartaoPorNumero(Mockito.any())).thenReturn(cartaoc);
			
		compraService.salvarCompras(compraDto);
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void salvarCompraMaqInexistenteTest() throws Exception {
		
		Mockito.when(maqRepository.findBySerial(Mockito.any())).thenReturn(null);
		
		Mockito.when(cartaoCreditoService.buscaCartaoPorNumero(Mockito.any())).thenReturn(cartaoc);
			
		compraService.salvarCompras(compraDto);
	}
	
	@Test
	public void buscarTodasCompraTest() {
	
		Mockito.when(compraRepository.findAll()).thenReturn(compras);
		
		List<Compra> compras = compraService.buscarTodos();
		
		assertNotNull(compras);
	}
	
	@Test
	public void buscarTodasComprasVazioTest() {
	
		Mockito.when(compraRepository.findAll()).thenReturn(null);
		
		List<Compra> compras = compraService.buscarTodos();
		
		assertNull(compras);
	}
	
	@Ignore
	@Test(expected = DataIntegrityException.class)
	public void enviarEmailTest() throws Exception {
		Compra compra = new Compra();
		compra.setId(1);
//		Mockito.when(maqRepository.findBySerial(Mockito.any())).thenReturn(maq);
//		
//		Mockito.when(cartaoCreditoService.buscaCartaoPorNumero(Mockito.any())).thenReturn(cartaoc);
		
	//	Mockito.when(emailService.sendOrderConfirmationHtmlEmail(Mockito.anyObject()))
		
		doThrow(new Exception())
        .when(emailService).sendOrderConfirmationHtmlEmail(Mockito.any());
		
		//Mockito.doThrow().when(compraService).enviarEmail(compra);
		
		compraService.salvarCompras(compraDto);
	}
}
