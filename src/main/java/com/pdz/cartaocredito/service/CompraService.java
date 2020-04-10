package com.pdz.cartaocredito.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdz.cartaocredito.entity.CartaoCredito;
import com.pdz.cartaocredito.entity.Compra;
import com.pdz.cartaocredito.entity.dto.CompraDTO;
import com.pdz.cartaocredito.repository.CompraRepository;

@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
    private CartaoCreditoService cartaoCreditoService;
	
	public Compra salvarCompras(CompraDTO compra) throws Exception {
		
		Compra compras = fromDTO(compra);
		  
		verificaInformacoesCartao(compras);
		
		return compraRepository.save(compras);
	}
	
	public Compra fromDTO(CompraDTO obj) {

		Compra compra = new Compra();
		CartaoCredito cartao = new CartaoCredito();
		
		compra.setDataCompra(obj.getDataCompra());
		compra.setValor(obj.getValor());
		compra.setLoja(obj.getLoja());
		cartao.setNumeroCartao(obj.getNumeroCartao());
		cartao.setCodSeguranca(obj.getCodSeguranca());

		compra.setCartaoCredito(cartao);
		
		return compra;
	}
	
	public Boolean verificaInformacoesCartao(Compra compras) throws Exception {
		
		if(cartaoCreditoService.verificaSeNumeroCartaoInvalidoExiste(compras.getCartaoCredito().getNumeroCartao()) == false) {
			throw new Exception();
		}else if(cartaoCreditoService.verificaNumeroCartaoCodSeguranca(compras.getCartaoCredito().getNumeroCartao(), 
																       compras.getCartaoCredito().getCodSeguranca())==false) {
			throw new Exception();
		}else if(cartaoCreditoService.verificaSeCartaoTemLimite(compras.getCartaoCredito().getNumeroCartao(), 
														  compras.getValor())==false){
			throw new Exception();
		}
		return true;
	}
}
