package com.pdz.cartaocredito.service.email;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.pdz.cartaocredito.entity.Compra;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
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

	protected String htmlFromTemplateCompraConfirmada(Compra compra) {
		
		Context context = new Context();
		
		context.setVariable("compra", compra);
		
		return templateEngine.process("email/confirmacaoCompra", context);
	}
	
	protected String htmlFromTemplateCompraNegada(Compra compra) {
		
		Context context = new Context();
		
		context.setVariable("compra", compra);
		
		return templateEngine.process("email/compraNegada", context);
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Compra compra) {
		
		try {
			MimeMessage mm = prepareMimeMessageFromCompra(compra);
			sendHtmlEmail(mm);
		} catch (Exception e) {
			sendOrderConfirmationEmail(compra);
		}
	}

	@Override
	public void sendOrderCompraNegadaHtmlEmail(Compra compra) {
		
		try {
			MimeMessage mm = prepareMimeMessageFromCompraNegada(compra);
			sendHtmlEmail(mm);
		} catch (Exception e) {
			sendOrderConfirmationEmail(compra);
		}
	}
	
	protected MimeMessage prepareMimeMessageFromCompra(Compra compra) throws MessagingException {
		
		MimeMessage mm = javaMailSender.createMimeMessage();
		
		MimeMessageHelper mmh = new MimeMessageHelper(mm, true);
		
		mmh.setTo(compra.getUsuario().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Compra confirmada! COD: " +compra.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateCompraConfirmada(compra),true);
		
		return mm;
	}
	
	protected MimeMessage prepareMimeMessageFromCompraNegada(Compra compra) throws MessagingException {
		
		MimeMessage mm = javaMailSender.createMimeMessage();
		
		MimeMessageHelper mmh = new MimeMessageHelper(mm, true);
		
		System.out.println("###############EMAIL: " +compra.getUsuario().getEmail());
		
		mmh.setTo(compra.getUsuario().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Compra Negada! Limite indisponivel");
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateCompraNegada(compra),true);
		
		return mm;
	}
}
