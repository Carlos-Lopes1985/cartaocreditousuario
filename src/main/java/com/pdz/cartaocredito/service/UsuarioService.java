package com.pdz.cartaocredito.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pdz.cartaocredito.entity.CartaoCredito;
import com.pdz.cartaocredito.entity.Cliente;
import com.pdz.cartaocredito.entity.Pessoa;
import com.pdz.cartaocredito.entity.dto.ClienteDTO;
import com.pdz.cartaocredito.exception.DataIntegrityException;
import com.pdz.cartaocredito.repository.CartaoCreditoRepository;
import com.pdz.cartaocredito.repository.PessoaRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public Cliente salvar(Cliente usu) throws Exception {
		
		try {
			
			pessoaRepository.save(usu);
			
			cartaoCreditoRepository.saveAll(usu.getCartoes());
		} catch (DataIntegrityException e) {
			throw new DataIntegrityException("Erro no cadastro do usuário");
		}
		return usu;
	}
	
	public List<Pessoa>buscarTodos(){
		return pessoaRepository.findAll();
	}
	
	public Pessoa buscarUsuario(Integer id) {
		return pessoaRepository.findById(id).get();
	}

	public Cliente fromDto(@Valid ClienteDTO objDto) {
		
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
	
		return usu;
	}
}
