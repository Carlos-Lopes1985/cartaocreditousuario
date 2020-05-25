
package com.pdz.cartaocredito.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdz.cartaocredito.entity.Loja;
import com.pdz.cartaocredito.entity.dto.LojaNovoDTO;
import com.pdz.cartaocredito.entity.dto.ResponsavelSalvarArquivoDTO;
import com.pdz.cartaocredito.exception.ObjectNotFoundException;
import com.pdz.cartaocredito.io.ArquivoIO;
import com.pdz.cartaocredito.repository.LojaRepository;
import com.pdz.cartaocredito.service.validations.util.CpfCnpj;
import com.pdz.cartaocredito.service.validations.util.DecodificarURL;

import jxl.read.biff.BiffException;

/**
 * Responsável pelas funcionalidades referentes a loja
 *  
 * @author zupper
 *
 */
@Service
public class LojaService {
	
	private static final Logger log = LoggerFactory.getLogger(LojaService.class);
	
	@Autowired
	private LojaRepository lojaRepository;
	
	/**
	 * Busca todas as lojas cadastradas no sistema
	 * 
	 * @return
	 */
	public List<Loja>buscarTodos(){
		log.info("Iniciando busca de todas as lojas, buscarTodos(){}.", Loja.class);
		return lojaRepository.findAll();
	}
	
	/**
	 * Busca loja pelo id passado pelo usuário
	 * 
	 * @param id
	 * @return
	 */
	public Loja buscarLoja(Integer id) {
		log.info("Iniciando de lojas por ID, buscarLoja(){}.", Loja.class);
		
		return lojaRepository.findById(id).orElseThrow(()-> 
			new ObjectNotFoundException("Objeto não encontrado! Id: " +id+ "Tipo: " +Loja.class));
	}
	
	/**
	 * Responsável por salvar uma loja no banco de dados
	 * 
	 * @param loja
	 * @return
	 */
	public Loja salvar(Loja loja) {
		log.info("Iniciando cadastro de lojas, salvar(){}.", Loja.class);
		
		if(loja == null) {
			log.error("Erro no cadastro de lojas(){}.", Loja.class);
			throw new ObjectNotFoundException("Loja não pode ser nulo!");
		}
		log.info("Fim cadastro de lojas, salvar(){}.", loja);
		
		return lojaRepository.save(loja);
	}
	
	/**
	 * Converte um objeto LojaNovoDTO em um objeto Loja
	 * 
	 * @param objDto
	 * @return
	 */
	public Loja fromDto(@Valid LojaNovoDTO objDto) {
		log.info("Iniciando conversão de LojaNovoDTO para Loja, fromDto(){}.", objDto);

		return new Loja(null, objDto.getNome(),objDto.getCnpj());
	}
	
	/**
	 * 
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	public ResponsavelSalvarArquivoDTO importExcelParaBanco(String caminho) throws BiffException, IOException {
		
		log.info("Iniciando importe de lojas para o banco de dados, importExcelParaBanco(){}.", Loja.class);
		
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
			log.info("Iniciando cadastro de lista de lojas, importExcelParaBanco(){}.", Loja.class);
		}else {
			log.error("Erro no cadastro, nenhum dado para salvar, importExcelParaBanco(){}.", Loja.class);
			
			throw new ObjectNotFoundException("Não existem registros para importar");
		}
		log.info("Fim importe de lojas para o banco de dados, importExcelParaBanco(){}.", resp);
		
		return resp;
	}
	
	/**
	 * Formata o caminho digitado pelo usuário
	 * 
	 * @param caminho
	 * @return
	 */
	public String formataStringCaminho(String caminho) {
		
		log.info("Iniciando formatação do caminho de import, formataStringCaminho(){}.", Loja.class);
		
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
		
		log.info("Iniciando cadastro de lista de lojas, salvarListaLojas(){}.", Loja.class);
		
		Loja lj;
		
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
		
		log.info("Iniciando verificação de lojas iguais, verificaLOjasIguais(){}.", cnpj);
		
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
	 *Responsável por validar lista do arquivo excel
	 * 
	 * @param lojas
	 * @return
	 */
	public static List<LojaNovoDTO> validaListaDoArquivoExcel(List<LojaNovoDTO> lojas, ResponsavelSalvarArquivoDTO resp) {
		
		log.info("Iniciando validação da lista de dados do arquivo, validaListaDoArquivoExcel(){}.", Loja.class);
		
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
		
		log.info("Fim validação da lista de dados do arquivo, validaListaDoArquivoExcel(){}.", lojas);
		
		return lojas;
	}
}
