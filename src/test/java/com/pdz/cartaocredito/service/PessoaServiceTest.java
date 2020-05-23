package com.pdz.cartaocredito.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pdz.cartaocredito.entity.CartaoCredito;
import com.pdz.cartaocredito.entity.Cliente;
import com.pdz.cartaocredito.entity.Pessoa;
import com.pdz.cartaocredito.entity.dto.ClienteDTO;
import com.pdz.cartaocredito.exception.DataIntegrityException;
import com.pdz.cartaocredito.exception.ObjectNotFoundException;
import com.pdz.cartaocredito.repository.CartaoCreditoRepository;
import com.pdz.cartaocredito.repository.PessoaRepository;

@RunWith(MockitoJUnitRunner.Silent.class) 
public class PessoaServiceTest {
	
	@InjectMocks
	private PessoaService pessoaService;
	
	@Mock
	private PessoaRepository pessoaRepository;
	
	@Mock
	private CartaoCreditoRepository cartaoRepository;
	
	@Mock
	private BCryptPasswordEncoder pe;
	
	private Cliente cliente;
	
	private List<CartaoCredito> cc = new ArrayList<CartaoCredito>();
	
	private List<Pessoa>pessoas = new ArrayList<Pessoa>();
	
	private ClienteDTO clienteDTO;
	
	/**
	 * Inicializa elementos comuns entre os testes
	 * 
	 */
	@Before
	public void setUp() {
		cliente = new Cliente(1,"Carlos",LocalDate.now(),"11792993706","12345","caka19_rj@hotmail.com");
		CartaoCredito cartao  = new CartaoCredito(null, "Master", "4235879000023233", "239", 2500., 1000., 200.,LocalDate.now(),LocalDate.now(), cliente);
		cc.add(cartao);
		pessoas.add(cliente);
		
		clienteDTO = new ClienteDTO(1, "Carlos", LocalDate.now(), "11792993706", "123", "carlos@hotmail.com", "Master", "22224444555577779999", "231", 500., 200., 400., LocalDate.now(), LocalDate.now());
	}
	
	@Test
	public void buscarTodosTest() {
		
		Mockito.when(pessoaRepository.findAll()).thenReturn(pessoas);
		when(pessoaService.buscarTodos()).thenReturn(pessoas);
		
		assertEquals(pessoas.size(), 1);
		assertNotNull(pessoas.size());
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void buscarUsuarioTest() {
		Mockito.when(pessoaRepository.findById(123)).thenReturn(Optional.empty());
		pessoaService.buscarUsuario(1);
	}
	
	@Test
	public void buscarUsuarioValidoTest() {
		Mockito.when(pessoaRepository.findById(123)).thenReturn(Optional.of(cliente));
		pessoaService.buscarUsuario(123);
	}
	
	@Test
	public void salvarSucessoTest() throws Exception {
		Mockito.when(pessoaRepository.save(Mockito.any())).thenReturn(cliente);
		Mockito.when(cartaoRepository.saveAll(Mockito.any())).thenReturn(cc);
		
		pessoaService.salvar(cliente);
	}
	
	@Test(expected = DataIntegrityException.class)
	public void salvarFalhaTest() throws Exception {
		Mockito.when(pessoaRepository.save(Mockito.any())).thenReturn(null);
		Mockito.when(cartaoRepository.saveAll(Mockito.any())).thenReturn(null);
		
		when(pessoaService.salvar(cliente)).thenReturn(null);
		
	}
	
	@Test
	public void fromDtoTest() {
		
		Pessoa pessoa = pessoaService.fromDto(clienteDTO);
		
		assertTrue(pessoa != null);
	}
}
