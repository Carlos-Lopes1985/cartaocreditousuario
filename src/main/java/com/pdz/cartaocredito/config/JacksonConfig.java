package com.pdz.cartaocredito.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdz.cartaocredito.entity.Cliente;
import com.pdz.cartaocredito.entity.Funcionario;


@Configuration
public class JacksonConfig {
	
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {

		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {

			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(Cliente.class);
				objectMapper.registerSubtypes(Funcionario.class);
				super.configure(objectMapper);
			}
		};
		return builder;
	}
	
}
