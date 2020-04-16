package com.pdz.cartaocredito.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pdz.cartaocredito.entity.MaquinaCartaoCredito;
import com.pdz.cartaocredito.repository.MaquinaCartaoCreditoRepository;
import com.pdz.cartaocredito.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private MaquinaCartaoCreditoRepository maquinaCartaoCreditoRepository;
	
	@Override
	public UserDetails loadUserByUsername(String serial) throws UsernameNotFoundException {
		
		MaquinaCartaoCredito  maq = maquinaCartaoCreditoRepository.findBySerial(serial);
		
		if(maq == null) {
			throw new UsernameNotFoundException(serial);
		}
		return new UserSS(maq.getId(), maq.getSerial(), maq.getSenha(), maq.getLoja().getPerfils());
	}

}
