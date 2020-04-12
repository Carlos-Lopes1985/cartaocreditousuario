package com.pdz.cartaocredito.service.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.pdz.cartaocredito.entity.Usuario;
import com.pdz.cartaocredito.exception.MethodFailureException;
import com.pdz.cartaocredito.repository.UsuarioRepository;

@Component
public class ValidaUsuario {
	
	@Autowired
    private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public Boolean verificaSenhaUsuario(Usuario usuario) throws Exception {
		
		Usuario user = usuarioRepository.findById(usuario.getIdUsuario()).get();
		
		if(user.getSenha() != pe.encode(usuario.getSenha())) {
			throw new MethodFailureException("Senha digitada é inválida");
		}
		return true;
	}
}