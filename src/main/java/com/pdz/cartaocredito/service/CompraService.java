package com.pdz.cartaocredito.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdz.cartaocredito.entity.CartaoCredito;
import com.pdz.cartaocredito.entity.Compra;
import com.pdz.cartaocredito.entity.Usuario;
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
		// objeto neste momento
		/* Compra [
		 *     id=null,
		 *     dataCompra=2020-04-10,
		 *     status=null,
		 *     valor=500.0,
		 *     loja=1,
		 *     qtdeParcela=null,
		 *     usuario=null,
		 *     cartaoCredito=CartaoCredito [
		 *         id=null,
		 *         bandeira=null,
		 *         numeroCartao=4235879000023233, 
		 *         codSeguranca=239,
		 *         limiteDisponivelTotal=null,
		 *         limiteDisponivelAtual=null,
		 *         limiteDisponivelParaSaque=null,
		 *         usuario=null
	     *     ]
	     * ]
		 */
		
		verificaInformacoesCartao(compras);
		
		return compraRepository.save(compras);
	}
	
	public Compra fromDTO(CompraDTO obj) {

		Compra        compra = new Compra();
		CartaoCredito cartao = cartaoCreditoService.buscaCartaoPorNumero(obj.getNumeroCartao());
		Usuario       usu    = new Usuario();
		
		compra.setDataCompra(obj.getDataCompra());
		compra.setValor(obj.getValor());
		compra.setLoja(obj.getLoja());
		cartao.setNumeroCartao(obj.getNumeroCartao());
		cartao.setCodSeguranca(obj.getCodSeguranca());
		cartao.setBandeira(cartao.getBandeira());
		cartao.setId(cartao.getId());
		usu.setIdUsuario(cartao.getUsuario().getIdUsuario());
		compra.setCartaoCredito(cartao);
		compra.setUsuario(usu);
		
		System.out.println(compra.toString());
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
