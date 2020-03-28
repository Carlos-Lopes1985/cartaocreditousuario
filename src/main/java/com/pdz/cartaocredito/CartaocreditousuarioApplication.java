package com.pdz.cartaocredito;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pdz.cartaocredito.entity.CartaoCredito;
import com.pdz.cartaocredito.entity.Usuario;
import com.pdz.cartaocredito.repository.CartaoCreditoRepository;
import com.pdz.cartaocredito.repository.UsuarioRepository;

@SpringBootApplication
public class CartaocreditousuarioApplication implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CartaocreditousuarioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Usuario user1 = new Usuario(null,"Carlos",LocalDate.now(),"11792993706","123");
		Usuario user2 = new Usuario(null,"Roberto",LocalDate.now(),"11792993707","123");
		Usuario user3 = new Usuario(null,"Juvenal",LocalDate.now(),"11792993708","123");
		
		CartaoCredito cc = new CartaoCredito(null, "Master", "423587900002323", "239", 2500., 1000., 200., user1);
		CartaoCredito cc1 = new CartaoCredito(null, "Visa",   "423587900002324", "238", 1500., 200., 400., user1);
		CartaoCredito cc2 = new CartaoCredito(null, "Alelo",  "423587900002325", "237", 5500., 2000., 600., user2);
		CartaoCredito cc3 = new CartaoCredito(null, "Sodex",  "423587900002326", "236", 7500., 4000., 2700., user3);
		
		usuarioRepository.saveAll(Arrays.asList(user1,user2,user3));
		cartaoCreditoRepository.saveAll(Arrays.asList(cc,cc1,cc2,cc3));
		
	}

}
