package com.pdz.cartaocredito.service;

import org.springframework.mail.SimpleMailMessage;

import com.pdz.cartaocredito.entity.Compra;

public abstract class AbstractEmailService implements EmailService {

	@Override
	public void sendOrderConfirmationEmail(Compra compra) {
	//	SimpleMailMessage sm = PSM
		
	}

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		// TODO Auto-generated method stub
		
	}
	
	
}
