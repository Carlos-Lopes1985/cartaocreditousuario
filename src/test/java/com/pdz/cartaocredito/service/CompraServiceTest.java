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
		
		 maq = new MaquinaCartaoCredito(1, "XPTO", "123",l);
		 compras.add(cc);
		 
		 Mockito.when(validaCompra.verificaInformacoesCartao(Mockito.any())).thenReturn(true);
			
		Mockito.when(validaUsuario.verificaSenhaUsuario(Mockito.any())).thenReturn(true);
			
		Mockito.when(cartaoCreditoService.buscaCartaoPorNumero(Mockito.any())).thenReturn(cartaoc);
	}
	

	/* Seu teste espera um objectnotfoundexception do seguinte método:

		return compraRepository.findById(id).orElseThrow(()->
		new ObjectNotFoundException("Objeto não encontrado! Id: " +id+ "Tipo: " +Compra.class));


		OU seja ele só vai retornar ObjectNotFoundException se ele nao encontrar nada no findById
		Ou seja vc tem q retornar um Optional vazio pra esse teste funcionar, se vc retornar um optional vazio vai ser como se
		ele nao tivesse encontrado nada na base e ai ele cairia orElseThrow e seu teste passaria com sucesso!

	 */
	@Test(expected = ObjectNotFoundException.class)
	public void buscarCompraInexistenteTest() {
			
		Mockito.when(compraRepository.findById(123)).thenReturn(Optional.of(cc));
		
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

	/* ESTE MÉTODO FAZ VÁRIOS CENÁRIOS DE TESTES
	Fluxos de teste:
	1 Cenário :
		1 - salvarCompras()
			2 - fromDTO(compra)
				3 - maqRepository.findBySerial()
					SE a maquina nao for encontrada ele vai retornar ObjectNotFoundException(" Maquina não encontrado! Id: " +obj.getSerial()+ "Tipo: " +MaquinaCartaoCredito.class);
	Exmeplo solucao: Em outras palavras se vc for fazer o teste desse cenário vc vai ter q mockar o maqRepository.findBySerial para retornar null!
=========================================================
	2 Cenário :
		1 - salvarCompras()
			2 - validaCompra.verificaInformacoesCartao(comprasObj)
				3 - validaCompra.verificaInformacoesCartao(comprasObj)
					*** ESSE METODO TEM 3 CASOS DE EXCEPTION
					 -throw new DataIntegrityException("Número de cartão inválido! "+ compras.getCartaoCredito().getNumeroCartao());
					 -throw new DataIntegrityException("Código de segurança inválido! "+ compras.getCartaoCredito().getCodSeguranca());
					 -throw new DataIntegrityException("Limite Indisponivel! "+ compras.getCartaoCredito().getLimiteDisponivelAtual());
					 	- Nesse caso ele ainda manda email enviarEmail(compras); talvez ainda precise mockar esse cara, ou simular exception com ele
	Exmeplo solucao: eu faria 3 casos de testes, um pra cada um desses
		Simularia passando numero de cartao invalido
		Simularia passando Código de segurança inválido
		Simularia passando Limite Indisponivel
=============================================================
	3 Cenário :
	1 - salvarCompras()
		2 - validaUsuario.verificaSenhaUsuario(comprasObj.getCliente());
			3 - usuarioRepository.findById()
			4 - if(!pe.matches(usuario.getSenha(),user.getSenha()))
				throw new MethodFailureException("Senha digitada é inválida");

	Exmeplo solucao: Nesse caso criaria um teste para validar se a senha é invalida
	Mockaria o metodo usuarioRepository.findById() para ele retornar um objeto com a senha diferente da q eu informei
	E dava um expect no MethodFailureException

=============================================================
	4 Cenário :
	1 - salvarCompras()

		try{
			enviarEmail(comprasObj);
		} catch (Exception e) {
			throw new DataIntegrityException("Email não enviado");
		}

	Eu analisei o metodo enviarEmail e nao vi nenhnuma situacao onde ele estoura exception, entao nao sei como testar essa exceção!
	Caso vc venha a ajustar esse fluxo ai seria o cenario onde o email n foi enviado

=============================================================
	5 Cenário : DE SUCESSO

	**** para o fluxo de sucesso vc vai precisar mockar tudo q mockamos anteriormente! e usar dados validos!
	Mocks:
		cartaoCreditoService.buscaCartaoPorNumero(obj.getNumeroCartao());
		maqRepository.findBySerial(obj.getSerial());
		emailService.sendOrderCompraNegadaHtmlEmail(compraObj)
		usuarioRepository.findById(usuario.getIdUsuario()).get()
		cartaoCreditoService.buscaCartaoPorNumero(compra.getNumeroCartao())
		cartaoCreditoService.salvar(cartao)
		compraRepository.save(comprasObj)
		emailService.sendOrderConfirmationHtmlEmail(compraObj)
	 */
	
	@Test(expected = DataIntegrityException.class)
	public void enviarEmailTest() throws Exception {
		Compra compra = new Compra();
		compra.setId(1);
//		Mockito.when(maqRepository.findBySerial(Mockito.any())).thenReturn(maq);
//		
//		Mockito.when(cartaoCreditoService.buscaCartaoPorNumero(Mockito.any())).thenReturn(cartaoc);
		
	//	Mockito.when(emailService.sendOrderConfirmationHtmlEmail(Mockito.anyObject()))
		
//		doThrow(new Exception())
//        .when(emailService).sendOrderConfirmationHtmlEmail(Mockito.any());
		
		//Mockito.doThrow().when(compraService).enviarEmail(compra);
		
		compraService.salvarCompras(compraDto);
	}
}
