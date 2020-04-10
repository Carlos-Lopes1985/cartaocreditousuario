package com.pdz.cartaocredito.service;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import com.pdz.cartaocredito.entity.CartaoCredito;
import com.pdz.cartaocredito.entity.Compra;
import com.pdz.cartaocredito.entity.Loja;
import com.pdz.cartaocredito.entity.Usuario;
import com.pdz.cartaocredito.repository.CartaoCreditoRepository;
import com.pdz.cartaocredito.repository.CompraRepository;
import com.pdz.cartaocredito.repository.LojaRepository;
import com.pdz.cartaocredito.repository.UsuarioRepository;

@Service
public class DBService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;
	
	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private LojaRepository lojaRepository;
	
	public void instanciateTestDatabase()throws ParseException{
		
		Usuario user1 = new Usuario(null,"Carlos",LocalDate.now(),"11792993706","123");
		Usuario user2 = new Usuario(null,"Roberto",LocalDate.now(),"11792993707","123");
		Usuario user3 = new Usuario(null,"Juvenal",LocalDate.now(),"11792993708","123");
		
		CartaoCredito cc  = new CartaoCredito(null, "Master", "4235879000023233", "239", 2500., 1000., 200.,LocalDate.now(), user1);
		CartaoCredito cc1 = new CartaoCredito(null, "Visa",   "4235879000023243", "238", 1500., 200., 400.,LocalDate.now(), user1);
		CartaoCredito cc2 = new CartaoCredito(null, "Alelo",  "4235879000023253", "237", 5500., 2000., 600.,LocalDate.now(), user2);
		CartaoCredito cc3 = new CartaoCredito(null, "Sodex",  "4235879000023263", "236", 7500., 4000., 2700.,LocalDate.now(), user3);
		
		Loja l1 = new Loja(null,"lojas americanas");
		Loja l2 = new Loja(null,"Magazine luiza");
		Loja l3 = new Loja(null,"Pernambucanas");
		Loja l4 = new Loja(null,"Mariza");
		
		lojaRepository.saveAll(Arrays.asList(l1,l2,l3,l4));
		
		Compra c1 = new Compra(null,LocalDate.now(),1,200.,10,l1,user3,cc);
		Compra c2 = new Compra(null,LocalDate.now(),1,500.,1,l2,user1,cc3);
		Compra c3 = new Compra(null,LocalDate.now(),1,300.,1,l2,user2,cc2);
		Compra c4 = new Compra(null,LocalDate.now(),1,400.,1,l3,user2,cc1);
		Compra c5 = new Compra(null,LocalDate.now(),1,700.,1,l1,user3,cc2);
		Compra c6 = new Compra(null,LocalDate.now(),1,500.,1,l2,user2,cc1);
		Compra c7 = new Compra(null,LocalDate.now(),1,200.,1,l3,user3,cc3);
		
		usuarioRepository.saveAll(Arrays.asList(user1,user2,user3));
		cartaoCreditoRepository.saveAll(Arrays.asList(cc,cc1,cc2,cc3));
		compraRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6,c7));
		
	}
}
