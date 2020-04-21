package com.pdz.cartaocredito.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.pdz.cartaocredito.service.DBService;
import com.pdz.cartaocredito.service.EmailService;
import com.pdz.cartaocredito.service.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDataBase() throws ParseException {
		
		if(!"create".equals(strategy)) {
			return false;
		}
		
		dbService.instanciateTestDatabase();
		
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
