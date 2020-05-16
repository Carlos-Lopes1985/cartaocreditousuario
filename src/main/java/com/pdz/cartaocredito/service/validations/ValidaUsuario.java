package com.pdz.cartaocredito.service.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.pdz.cartaocredito.entity.Pessoa;
import com.pdz.cartaocredito.exception.MethodFailureException;
import com.pdz.cartaocredito.repository.PessoaRepository;

@Component
public class ValidaUsuario {
	
	@Autowired
    private PessoaRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	/**
	 * 
	 * @param usuario
	 * @return
	 * @throws Exception
	 */
	public Boolean verificaSenhaUsuario(Pessoa usuario) throws Exception {
		
		Pessoa user = usuarioRepository.findById(usuario.getIdUsuario()).get();
		
		if(!pe.matches(usuario.getSenha(),user.getSenha())) {
			
			throw new MethodFailureException("Senha digitada é inválida");
		}
		return true;
	}
}
