package com.pdz.cartaocredito.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pdz.cartaocredito.entity.CartaoCredito;
import com.pdz.cartaocredito.entity.Cliente;
import com.pdz.cartaocredito.entity.Pessoa;
import com.pdz.cartaocredito.entity.dto.ClienteDTO;
import com.pdz.cartaocredito.exception.DataIntegrityException;
import com.pdz.cartaocredito.exception.ObjectNotFoundException;
import com.pdz.cartaocredito.repository.CartaoCreditoRepository;
import com.pdz.cartaocredito.repository.PessoaRepository;

/**
 * Responsável pelas operações dos usuários no sistema
 * 
 * @author zupper
 *
 */
@Service
public class UsuarioService {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	/**
	 * 
	 * @param usu
	 * @return
	 * @throws Exception
	 */
	public Cliente salvar(Cliente usu) throws Exception {
		
		try {
			log.info("Iniciando cadastro de Cliente, salvar(){}.");
			pessoaRepository.save(usu);
			cartaoCreditoRepository.saveAll(usu.getCartoes());
		} catch (DataIntegrityException e) {
			log.error("Erro no cadastro de Cliente, salvar(){}.", usu);
			throw new DataIntegrityException("Erro no cadastro do usuário");
		}
		log.info("Fim cadastro de Cliente, salvar(){}.");
		
		return usu;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Pessoa>buscarTodos(){
		log.info("Iniciando busca de todos os clientes, buscarTodos(){}.");
		return pessoaRepository.findAll();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Pessoa buscarUsuario(Integer id) {
		
		log.info("Iniciando busca de cliente por ID, buscarUsuario(){}.", id);
		
		return pessoaRepository.findById(id).orElseThrow(()-> 
		new ObjectNotFoundException("Objeto não encontrado! Id: " +id+ "Tipo: " +Pessoa.class));
	}

	/**
	 * 
	 * @param objDto
	 * @return
	 */
	public Cliente fromDto(@Valid ClienteDTO objDto) {
		
		log.info("Iniciando conversão de um objeto dto para um Cliente, fromDto(){}.");
		
		Cliente usu = new Cliente(null, objDto.getNome(),
				 objDto.getDataNascimento(),
				 objDto.getCpf(),
				 pe.encode(objDto.getSenha()),
				 objDto.getEmail());
		
		CartaoCredito cartao = 
				new CartaoCredito(null, objDto.getBandeira(), objDto.getNumeroCartao(), objDto.getCodSeguranca(), 
				objDto.getLimiteDisponivelAtual(), objDto.getLimiteDisponivelTotal(), objDto.getLimiteDisponivelParaSaque(), LocalDate.now(),
				objDto.getVencimentoFatura(), usu);

		Set<CartaoCredito>cartoes = new HashSet<>();
		cartoes.add(cartao);
		usu.setCartoes(cartoes);
		
		log.info("Fim conversão de um objeto dto para um Cliente, fromDto(){}.", usu);
		
		return usu;
	}
}
