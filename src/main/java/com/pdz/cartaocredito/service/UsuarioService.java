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
import com.pdz.cartaocredito.entity.Usuario;
import com.pdz.cartaocredito.entity.dto.UsuarioDTO;
import com.pdz.cartaocredito.repository.CartaoCreditoRepository;
import com.pdz.cartaocredito.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public Usuario salvar(Usuario usu) throws Exception {
		
		try {
			usuarioRepository.save(usu);
			
			cartaoCreditoRepository.saveAll(usu.getCartoes());
		} catch (Exception e) {
			throw new Exception();
		}
		return usu;
	}
	
	public List<Usuario>buscarTodos(){
		return usuarioRepository.findAll();
	}
	
	public Usuario buscarUsuario(Integer id) {
		return usuarioRepository.findById(id).get();
	}

	public Usuario fromDto(@Valid UsuarioDTO objDto) {
		
		Usuario usu = new Usuario(null, objDto.getNome(),
				 objDto.getDataNascimento(),
				 objDto.getCpf(),
				 pe.encode(objDto.getSenha()),
				 objDto.getEmail());
		
		CartaoCredito cartao = 
				new CartaoCredito(null, objDto.getBandeira(), objDto.getNumeroCartao(), objDto.getCodSeguranca(), 
				objDto.getLimiteDisponivelAtual(), objDto.getLimiteDisponivelTotal(), objDto.getLimiteDisponivelParaSaque(), LocalDate.now(), usu);

		Set<CartaoCredito>cartoes = new HashSet<>();
		cartoes.add(cartao);
		usu.setCartoes(cartoes);
		
		return usu;
	}
}
