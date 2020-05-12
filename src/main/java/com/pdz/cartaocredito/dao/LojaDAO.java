package com.pdz.cartaocredito.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pdz.cartaocredito.entity.Compra;
import com.pdz.cartaocredito.entity.Loja;

@Repository
public class LojaDAO {

	@PersistenceContext
	private EntityManager manager;
	
	//List<CompraExportaResponseDTO>buscaComprasLojasUsuarios(CompraExportarDTO compraObj){
	// faz uma api e chama .. ja esta feita acredito eu... vou ver
	// testa de
	public void buscaComprasLojasUsuarios() {
//		System.out.println("entrou aqui e deu erro");
//	
//		if (em == null) {
//			System.out.println("em está nulo");
//		}
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery<Compra> cq = cb.createQuery(Compra.class);
//		List<Compra> compras = em.createQuery(cq).getResultList();
//		
//		for (Compra compra : compras) {
//			System.out.println(compra);
//		}
//		//tem como testar isso?acredito que não.. vou tentar
	}
	

	public List<Loja> listar() {
		
		System.out.println("PASSOU AKI ---->");
		
		if(manager == null) {
			System.out.println("MANAGER NULLO!!!");
		}
		
		List<Loja> list = manager.createNativeQuery("SELECT * FROM loja", Loja.class).getResultList();
		
		return list;
	}
}
