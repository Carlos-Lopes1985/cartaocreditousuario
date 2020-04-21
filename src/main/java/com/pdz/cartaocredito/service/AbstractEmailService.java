package com.pdz.cartaocredito.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.pdz.cartaocredito.entity.Compra;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Compra compra) {
		SimpleMailMessage sm = prepareSimpleMailMessage(compra);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessage(Compra compra) {
		SimpleMailMessage sm = new SimpleMailMessage();
		
		sm.setTo(compra.getUsuario().getEmail());	
		sm.setFrom(sender);
		sm.setSubject("Compra confirmada: Código: "+compra.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(compra.toString());
		
		return sm;
	}

	
	
	
}
