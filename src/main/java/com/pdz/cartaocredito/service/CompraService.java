package com.pdz.cartaocredito.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdz.cartaocredito.dao.LojaDAO;
import com.pdz.cartaocredito.entity.CartaoCredito;
import com.pdz.cartaocredito.entity.Compra;
import com.pdz.cartaocredito.entity.Loja;
import com.pdz.cartaocredito.entity.MaquinaCartaoCredito;
import com.pdz.cartaocredito.entity.Usuario;
import com.pdz.cartaocredito.entity.dto.CompraDTO;
import com.pdz.cartaocredito.entity.dto.CompraExportarDTO;
import com.pdz.cartaocredito.exception.DataIntegrityException;
import com.pdz.cartaocredito.exception.ObjectNotFoundException;
import com.pdz.cartaocredito.repository.CompraRepository;
import com.pdz.cartaocredito.repository.MaquinaCartaoCreditoRepository;
import com.pdz.cartaocredito.repository.UsuarioRepository;
import com.pdz.cartaocredito.service.email.EmailService;
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
	
	@Autowired
	private EmailService emailService;
	
	/**
	 * Responsável por salvar uma compra realizada 
	 * 
	 * @param compra
	 * @return
	 * @throws Exception
	 */
	public Compra salvarCompras(CompraDTO compra) throws Exception {
		
		Compra comprasObj = fromDTO(compra); 
		
		validaCompra.verificaInformacoesCartao(comprasObj);
		validaUsuario.verificaSenhaUsuario(comprasObj.getUsuario());
		
		atualizaLimiteDisponivel(compra);
		
		compraRepository.save(comprasObj);
		
		try{
			enviarEmail(comprasObj);
		} catch (Exception e) {
			throw new DataIntegrityException("Email não enviado");
		} 
		return comprasObj;
	}
	
	/**
	 * Responsável pelo envio de email
	 * 
	 * @param compraObj
	 * @throws Exception
	 */
	public void enviarEmail(Compra compraObj) throws Exception {

		emailService.sendOrderConfirmationHtmlEmail(compraObj);
	}
	
	/**
	 * Converte um obj CompraDTO em um objeto Compra
	 * 
	 * @param obj
	 * @return
	 */
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
		usu.setEmail(cartao.getUsuario().getEmail());
		compra.setUsuario(usu);
		loja.setId(maq.getLoja().getId());
		compra.setLoja(loja);
		
		System.out.println("SENHA FROM DTO: "+obj.getSenha());
		
		return compra;
	}
	
	/**
	 * Atualiza o limite do cartão de crédito
	 * 
	 * @param compra
	 * @throws Exception
	 */
	public void atualizaLimiteDisponivel(CompraDTO compra)throws Exception{
		
		CartaoCredito cartao = cartaoCreditoService.buscaCartaoPorNumero(compra.getNumeroCartao());
		
		cartao.setId(cartao.getId());
		cartao.setLimiteDisponivelAtual( cartao.getLimiteDisponivelAtual() - compra.getValor()
				);
		
		cartaoCreditoService.salvar(cartao);
		
	}
	
	/**
	 * Busca todas as compras realizadas
	 * 
	 * @return
	 */
	public List<Compra> buscarTodos() {
		return compraRepository.findAll();
	}

	/**
	 * Busca uma compra a partir do id passado como parametro
	 * 
	 * @param id
	 * @return
	 */
	public Compra buscarCompra(Integer id) {
		
		Compra compra = new Compra();
		
		try {
			compra = compraRepository.findById(id).get();
		} catch (Exception e) {
			throw new ObjectNotFoundException("Compra não encontrada!");
		}
		return compra;
	}

	/**
	 * 
	 * @param compra
	 */
	public void exportaExcelCompras(@Valid CompraExportarDTO compra) {
		LojaDAO lj = new LojaDAO();
		List<Loja> lojas = new ArrayList<Loja>();
		lj.listar();
		for (int i = 0; i < lojas.size(); i++) {
			System.out.println("##########NOME LOJA######"+lojas.get(i).getNome());
		}
		lj.buscaComprasLojasUsuarios();
	}
}
