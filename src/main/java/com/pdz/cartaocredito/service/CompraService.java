package com.pdz.cartaocredito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdz.cartaocredito.entity.CartaoCredito;
import com.pdz.cartaocredito.entity.Compra;
import com.pdz.cartaocredito.entity.Loja;
import com.pdz.cartaocredito.entity.MaquinaCartaoCredito;
import com.pdz.cartaocredito.entity.Usuario;
import com.pdz.cartaocredito.entity.dto.CompraDTO;
import com.pdz.cartaocredito.exception.ObjectNotFoundException;
import com.pdz.cartaocredito.repository.CompraRepository;
import com.pdz.cartaocredito.repository.MaquinaCartaoCreditoRepository;
import com.pdz.cartaocredito.service.validations.ValidaCartaoCredito;
import com.pdz.cartaocredito.service.validations.ValidaUsuario;

@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
    private CartaoCreditoService cartaoCreditoService;
	
	@Autowired
	private ValidaCartaoCredito validaCompra;
	
	@Autowired
	private ValidaUsuario validaUsuario;
	
	@Autowired
	private MaquinaCartaoCreditoRepository maqRepository;
	
	public Compra salvarCompras(CompraDTO compra) throws Exception {
		
		Compra comprasObj = fromDTO(compra);
		
		validaCompra.verificaInformacoesCartao(comprasObj);
		validaUsuario.verificaSenhaUsuario(comprasObj.getUsuario());
		
		atualizaLimiteDisponivel(compra);
		
		return compraRepository.save(comprasObj);
	}
	
	public Compra fromDTO(CompraDTO obj) {

		Compra            compra = new Compra();
		CartaoCredito     cartao = cartaoCreditoService.buscaCartaoPorNumero(obj.getNumeroCartao());
		Usuario              usu = new Usuario();
		MaquinaCartaoCredito maq = maqRepository.findBySerial(obj.getSerial());
		Loja                loja = new Loja();
		
		compra.setDataCompra(obj.getDataCompra());
		compra.setValor(obj.getValor());
		cartao.setNumeroCartao(obj.getNumeroCartao());
		cartao.setCodSeguranca(obj.getCodSeguranca());
		cartao.setBandeira(cartao.getBandeira());
		cartao.setId(cartao.getId());
		usu.setIdUsuario(cartao.getUsuario().getIdUsuario());
		usu.setSenha(obj.getSenha());
		compra.setCartaoCredito(cartao);
		compra.setUsuario(usu);
		loja.setId(maq.getLoja().getId());
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

	public List<Compra> buscarTodos() {
		return compraRepository.findAll();
	}

	public Compra buscarCompra(Integer id) {
		
		Compra compra = new Compra();
		
		try {
			compra = compraRepository.findById(id).get();
		} catch (Exception e) {
			throw new ObjectNotFoundException("Compra não encontrada!");
		}
		
		return compra;
	}
	
}
