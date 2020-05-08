package com.pdz.cartaocredito.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdz.cartaocredito.dao.LojaDAO;
import com.pdz.cartaocredito.entity.Loja;
import com.pdz.cartaocredito.entity.dto.CompraExportaResponseDTO;
import com.pdz.cartaocredito.entity.dto.LojaNovoDTO;
import com.pdz.cartaocredito.entity.dto.ResponsavelSalvarArquivoDTO;
import com.pdz.cartaocredito.exception.ObjectNotFoundException;
import com.pdz.cartaocredito.io.ArquivoIO;
import com.pdz.cartaocredito.repository.LojaRepository;
import com.pdz.cartaocredito.service.validations.util.CpfCnpj;
import com.pdz.cartaocredito.service.validations.util.DecodificarURL;

import jxl.read.biff.BiffException;

@Service
public class LojaService {
	
	@Autowired
	private LojaRepository lojaRepository;
	
	/**
	 * Busca todas as lojas cadastradas no sistema
	 * 
	 * @return
	 */
	public List<Loja>buscarTodos(){
		return lojaRepository.findAll();
	}
	
	/**
	 * Busca loja pelo id passado pelo usuário
	 * 
	 * @param id
	 * @return
	 */
	public Loja buscarLoja(Integer id) {
		
		Loja lojaObj = lojaRepository.findById(id).get();
		
		if(lojaObj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " +id+ "Tipo: " +Loja.class);
		}
		return lojaObj;
	}
	
	/**
	 * Responsável por salvar uma loja no banco de dados
	 * 
	 * @param loja
	 * @return
	 */
	public Loja salvar(Loja loja) {
		
		if(loja == null) {
			throw new ObjectNotFoundException("Loja não pode ser nulo!");
		}
		return lojaRepository.save(loja);
	}
	
	/**
	 * Converte um objeto LojaNovoDTO em um objeto Loja
	 * 
	 * @param objDto
	 * @return
	 */
	public Loja fromDto(@Valid LojaNovoDTO objDto) {
		
		return new Loja(null, objDto.getNome(),objDto.getCnpj());
	}
	
	/**
	 * 
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	public ResponsavelSalvarArquivoDTO importExcelParaBanco(String caminho) throws BiffException, IOException {
		
		List<LojaNovoDTO> lojas          = new ArrayList<LojaNovoDTO>();
		List<LojaNovoDTO> lojasAux       = new ArrayList<LojaNovoDTO>();
		ResponsavelSalvarArquivoDTO resp = new ResponsavelSalvarArquivoDTO();
		
		lojas = ArquivoIO.getInstance()
						 .importaExcel(formataStringCaminho
								      (DecodificarURL.decodeValue(caminho)));
		
		List<LojaNovoDTO> lojasArq = validaListaDoArquivoExcel(lojas,resp);
		
		for (int i = 0; i < lojasArq.size(); i++) {
		
			String novoCnpj = CpfCnpj.removeMascaraCnpj(lojas.get(i).getCnpj());
			
			lojasArq.get(i).setCnpj(novoCnpj);
			
			if(verificaLojasIguais(lojasArq.get(i).getCnpj())) {
				lojasAux.add(lojasArq.get(i));
			}
		}
		
		if(!lojasAux.isEmpty()) {
			salvaListaLojas(lojasAux);
		}else {
			throw new ObjectNotFoundException("Não existem registros para importar");
		}
		return resp;
	}
	
	/**
	 * Formata o caminho digitado pelo usuário
	 * 
	 * @param caminho
	 * @return
	 */
	public String formataStringCaminho(String caminho) {
		
		StringBuilder sb = new StringBuilder(caminho);
        sb = sb.insert(2,"/");
		
		return sb.toString();
	}
	
	/**
	 * Responsável por salvar uma lista de lojas
	 * 
	 * @param lojas
	 */
	public void salvaListaLojas(List<LojaNovoDTO>lojas) {
		
		Loja lj = new Loja(); //porque instancia?
		
		for (int i = 0; i < lojas.size(); i++) {
			
			lj = fromDto(new LojaNovoDTO(lojas.get(i).getNome(), lojas.get(i).getCnpj()));
			
			lj.setCnpj(lj.getCnpj().replace(".", "").replace("/", "").replace("-", ""));
			
			lojaRepository.save(lj);
		}
	}
	
	/**
	 * Verifica se já existe cnpj cadastrado e se o cnpj é válido
	 * 
	 * @param cnpj
	 * @return
	 */
	public boolean verificaLojasIguais(String cnpj) {
		
		if(!CpfCnpj.isValidCNPJ(cnpj)){
			return false;
		}
		
		Loja loja = lojaRepository.findByCnpj(cnpj);
		
		if(loja != null) {
			return false;
		} 
		return true;
	}
	
	/**
	 * 
	 * @param lojas
	 * @return
	 */
	public static List<LojaNovoDTO> validaListaDoArquivoExcel(List<LojaNovoDTO> lojas, ResponsavelSalvarArquivoDTO resp) {
		
		List<LojaNovoDTO>lojaAux           = new ArrayList<LojaNovoDTO>();
		List<String>     cnpjNaoImportados = new ArrayList<String>();
		
		for (int i = 0; i < lojas.size(); i++) {

			String cnpj = CpfCnpj.removeMascaraCnpj(lojas.get(i).getCnpj());
			
			Long stream = lojas.stream().filter(loja -> CpfCnpj.removeMascaraCnpj(loja.getCnpj()).equals(cnpj)).count();
			
			if(stream > 1) {
				lojaAux.add(lojas.get(i));
				cnpjNaoImportados.add(lojas.get(i).getCnpj().concat( " - ").concat(lojas.get(i).getNome()));
			}
		}
		
		lojas.removeAll(lojaAux);
		
		resp.setContadorImportados(lojas.size());
		resp.setRegistrosNaoImportados(cnpjNaoImportados);
		
		return lojas;
	}
	
	public void buscarLojasPorCnpjData(String cnpj)throws ObjectNotFoundException{
		
	//	Loja loja = lojaRepository.findByCnpj(cnpj);
		
		List<Loja> lojas = new ArrayList<Loja>();
		
		LojaDAO lj = new LojaDAO();
		
		lojas = lj.listar();
		
		for (int i = 0; i < lojas.size(); i++) {
			System.out.println("##########NOME LOJA######"+lojas.get(i).getNome());
		}
	//	System.out.println(loja.toString());
		
		//return null;
	}
}
