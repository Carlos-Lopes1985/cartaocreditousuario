package com.pdz.cartaocredito.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pdz.cartaocredito.entity.Pessoa;
import com.pdz.cartaocredito.repository.PessoaRepository;
import com.pdz.cartaocredito.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Pessoa  pes = pessoaRepository.findByEmail(email);
		
		if(pes == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(pes.getIdUsuario(), pes.getEmail(), pes.getSenha(), pes.getPerfils());
	}

}
