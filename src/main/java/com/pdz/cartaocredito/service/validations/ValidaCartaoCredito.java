package com.pdz.cartaocredito.service.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pdz.cartaocredito.entity.CartaoCredito;
import com.pdz.cartaocredito.entity.Compra;
import com.pdz.cartaocredito.repository.CartaoCreditoRepository;
import com.pdz.cartaocredito.service.CartaoCreditoService;

@Component
public class ValidaCartaoCredito {
	
	@Autowired
    private CartaoCreditoService cartaoCreditoService;
	
	@Autowired
    private CartaoCreditoRepository cartaoCreditoRepository;
	
	public Boolean verificaInformacoesCartao(Compra compras) throws Exception {
		
		if(verificaSeNumeroCartaoInvalidoExiste(compras.getCartaoCredito().getNumeroCartao()) == false) {
			throw new Exception();
		}else if(verificaNumeroCartaoCodSeguranca(compras.getCartaoCredito().getNumeroCartao(), 
																       compras.getCartaoCredito().getCodSeguranca())==false) {
			throw new Exception();
		}else if(verificaSeCartaoTemLimite(compras.getCartaoCredito().getNumeroCartao(), 
														  compras.getValor())==false){
			throw new Exception();
		}
		return true;
	}
	
	public boolean verificaSeCartaoTemLimite(String numero, Double valor) {
		
		CartaoCredito cc  = cartaoCreditoService.buscaCartaoPorNumero(numero);
		Boolean       bOk = false;
		
		if(valor < cc.getLimiteDisponivelAtual()) 
			bOk = true;
		
		return bOk;
	}
	
	public boolean verificaSeNumeroCartaoInvalidoExiste(String numero) {
		
		Boolean bOk = true;
		
		if(numero.length() != 16) {
			bOk = false;
		}else {
			try {
				cartaoCreditoService.buscaCartaoPorNumero(numero);
			} catch (Exception e) {
				bOk = false;
			}
		}
		return bOk;
	}
	
	public boolean verificaNumeroCartaoCodSeguranca(String numero, String cod) {
		
		Boolean bOk = true;
		
		try {
			cartaoCreditoRepository.findByNumeroCartaoAndCodSeguranca(numero, cod);
		} catch (Exception e) {
			bOk=false;
		}
		
		return bOk;
	}
}
