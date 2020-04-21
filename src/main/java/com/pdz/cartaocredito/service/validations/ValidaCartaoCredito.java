package com.pdz.cartaocredito.service.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pdz.cartaocredito.entity.CartaoCredito;
import com.pdz.cartaocredito.entity.Compra;
import com.pdz.cartaocredito.exception.DataIntegrityException;
import com.pdz.cartaocredito.repository.CartaoCreditoRepository;
import com.pdz.cartaocredito.service.CartaoCreditoService;
import com.pdz.cartaocredito.service.email.EmailService;

@Component
public class ValidaCartaoCredito {
	
	@Autowired
    private CartaoCreditoService cartaoCreditoService;
	
	@Autowired
    private CartaoCreditoRepository cartaoCreditoRepository;
	
	@Autowired
	private EmailService emailService;
	
	/**
	 * Valida as informações do cartão de crédito do usuário
	 * 
	 * @param compras
	 * @return
	 * @throws Exception
	 */
	public Boolean verificaInformacoesCartao(Compra compras) throws Exception {
		
		if(verificaSeNumeroCartaoInvalidoExiste(compras.getCartaoCredito().getNumeroCartao()) == false) {
			throw new DataIntegrityException("Número de cartão inválido! "+ compras.getCartaoCredito().getNumeroCartao());
		}else if(verificaNumeroCartaoCodSeguranca(compras.getCartaoCredito().getNumeroCartao(), 
																       compras.getCartaoCredito().getCodSeguranca())==false) {
			throw new DataIntegrityException("Código de segurança inválido! "+ compras.getCartaoCredito().getCodSeguranca());
		}else if(verificaSeCartaoTemLimite(compras.getCartaoCredito().getNumeroCartao(), 
														  compras.getValor())==false){
			enviarEmail(compras);
			
			throw new DataIntegrityException("Limite Indisponivel! "+ compras.getCartaoCredito().getLimiteDisponivelAtual());
		}
		return true;
	}
	
	/**
	 * Responsável pelo envio de email
	 * 
	 * @param compraObj
	 * @throws Exception
	 */
	public void enviarEmail(Compra compraObj) throws Exception {
		emailService.sendOrderCompraNegadaHtmlEmail(compraObj);
	}
	
	/**
	 * Verifica se o cartão tem limite disponivel para a compra
	 * 
	 * @param numero
	 * @param valor
	 * @return
	 */
	public boolean verificaSeCartaoTemLimite(String numero, Double valor) {
		
		CartaoCredito cc  = cartaoCreditoService.buscaCartaoPorNumero(numero);
		Boolean       bOk = false;
		
		if(valor < cc.getLimiteDisponivelAtual()) 
			bOk = true;
		
		return bOk;
	}
	
	/**
	 * Verifica se o númedo do cartão é válido 
	 * 
	 * @param numero
	 * @return
	 */
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
	
	/**
	 * Verifica o número do cartão e o cod de segurança
	 * 
	 * @param numero
	 * @param cod
	 * @return
	 */
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
