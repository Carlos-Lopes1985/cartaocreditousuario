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
		
		usuario.setSenha(pe.encode(usuario.getSenha()));
		
		System.out.println("1 - ############# "+ usuario.getSenha());
		
		if(!(user.getSenha().equals(pe.encode(usuario.getSenha())))) {
			
			System.out.println("2 - ############################ SENHA BANCO" +user.getSenha());
			
			System.out.println("3 - ############################ SENHA DIGITADA" +usuario.getSenha());
			throw new MethodFailureException("Senha digitada é inválida");
			
		}
		return true;
	}
}
