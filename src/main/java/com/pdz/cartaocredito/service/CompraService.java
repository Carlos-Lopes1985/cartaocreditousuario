package com.pdz.cartaocredito.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdz.cartaocredito.entity.CartaoCredito;
import com.pdz.cartaocredito.entity.Compra;
import com.pdz.cartaocredito.entity.Loja;
import com.pdz.cartaocredito.entity.Usuario;
import com.pdz.cartaocredito.entity.dto.CompraDTO;
import com.pdz.cartaocredito.repository.CompraRepository;
import com.pdz.cartaocredito.service.validations.ValidaCartaoCredito;

@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
    private CartaoCreditoService cartaoCreditoService;
	
	@Autowired
	private ValidaCartaoCredito validaCompra;
	
	@Autowired
	private LojaService lojaService;
	
	public Compra salvarCompras(CompraDTO compra) throws Exception {
		
		Compra compras = fromDTO(compra);
		
		validaCompra.verificaInformacoesCartao(compras);
		
		atualizaLimiteDisponivel(compra);
		
		return compraRepository.save(compras);
	}
	
	public Compra fromDTO(CompraDTO obj) {

		Compra        compra = new Compra();
		CartaoCredito cartao = cartaoCreditoService.buscaCartaoPorNumero(obj.getNumeroCartao());
		Usuario       usu    = new Usuario();
		Loja          loja   = lojaService.buscarLoja(obj.getLoja());
		
		compra.setDataCompra(obj.getDataCompra());
		compra.setValor(obj.getValor());
		cartao.setNumeroCartao(obj.getNumeroCartao());
		cartao.setCodSeguranca(obj.getCodSeguranca());
		cartao.setBandeira(cartao.getBandeira());
		cartao.setId(cartao.getId());
		usu.setIdUsuario(cartao.getUsuario().getIdUsuario());
		compra.setCartaoCredito(cartao);
		compra.setUsuario(usu);
		loja.setId(loja.getId());
		compra.setLoja(loja);
		
		return compra;
	}
	
	public void atualizaLimiteDisponivel(CompraDTO compra)throws Exception{
		
		CartaoCredito cartao = cartaoCreditoService.buscaCartaoPorNumero(compra.getNumeroCartao());
		
		cartao.setId(cartao.getId());
		cartao.setLimiteDisponivelAtual( cartao.getLimiteDisponivelAtual() - compra.getValor()
				);
		
		cartaoCreditoService.salvar(cartao);
		
	}
	
}
