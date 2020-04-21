package com.pdz.cartaocredito.service;

import org.springframework.mail.SimpleMailMessage;

import com.pdz.cartaocredito.entity.Compra;

public interface EmailService {

	void sendOrderConfirmationEmail(Compra compra);
	
	void sendEmail(SimpleMailMessage msg);
}
