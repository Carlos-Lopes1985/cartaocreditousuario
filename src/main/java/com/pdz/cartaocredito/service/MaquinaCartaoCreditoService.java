package com.pdz.cartaocredito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdz.cartaocredito.entity.MaquinaCartaoCredito;
import com.pdz.cartaocredito.enums.Perfil;
import com.pdz.cartaocredito.exception.AuthorizationException;
import com.pdz.cartaocredito.exception.ObjectNotFoundException;
import com.pdz.cartaocredito.repository.MaquinaCartaoCreditoRepository;
import com.pdz.cartaocredito.security.UserSS;

@Service
public class MaquinaCartaoCreditoService {

	@Autowired
	private MaquinaCartaoCreditoRepository maquinaCartaoCreditoRepository;
	
	public List<MaquinaCartaoCredito> buscarTodos() {
		return maquinaCartaoCreditoRepository.findAll();
	}

	public MaquinaCartaoCredito buscarMaquina(Integer id) {
	
		UserSS user = UserService.authenticated();
		
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		MaquinaCartaoCredito maq = maquinaCartaoCreditoRepository.findById(id).get();
		
		if(maq == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " +id+ "Tipo: " +MaquinaCartaoCredito.class);
		}
		
		return maq;
	}

}
