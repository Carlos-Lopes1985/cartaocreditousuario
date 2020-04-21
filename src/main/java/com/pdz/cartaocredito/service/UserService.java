package com.pdz.cartaocredito.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.pdz.cartaocredito.security.UserSS;

public class UserService {

	/**
	 * Responsável por identificar o usuário que esta logado no sistema
	 * 
	 * @return
	 */
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
