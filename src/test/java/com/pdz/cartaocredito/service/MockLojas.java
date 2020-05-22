package com.pdz.cartaocredito.service;

import java.util.ArrayList;
import java.util.List;

import com.pdz.cartaocredito.entity.dto.LojaNovoDTO;

public class MockLojas {

	public static List<LojaNovoDTO> retornaListaLojasCnpjValidos() {

		List<LojaNovoDTO> lojas = new ArrayList<LojaNovoDTO>();

		LojaNovoDTO lj1 = new LojaNovoDTO("Carrefour", "56866005000156");
		LojaNovoDTO lj2 = new LojaNovoDTO("Carrefour", "98498065000103");
		LojaNovoDTO lj3 = new LojaNovoDTO("Carrefour", "06756072000140");

		lojas.add(lj1);
		lojas.add(lj2);
		lojas.add(lj3);

		return lojas;
	}

	public static List<LojaNovoDTO> retornaListaLojasCnpjValidosComPontuacao() {

		List<LojaNovoDTO> lojas = new ArrayList<LojaNovoDTO>();

		LojaNovoDTO lj1 = new LojaNovoDTO("Carrefour", "24.586.950/0001-08");
		LojaNovoDTO lj2 = new LojaNovoDTO("Carrefour", "10.619.767/0001-57");
		LojaNovoDTO lj3 = new LojaNovoDTO("Carrefour", "48.290.709/0001-03");

		lojas.add(lj1);
		lojas.add(lj2);
		lojas.add(lj3);

		return lojas;
	}

	public static List<LojaNovoDTO> retornaListaLojasCnpjValidosIguais() {
		List<LojaNovoDTO> lojas = new ArrayList<LojaNovoDTO>();

		LojaNovoDTO lj1 = new LojaNovoDTO("Carrefour", "24.586.950/0001-08");
		LojaNovoDTO lj2 = new LojaNovoDTO("Carrefour", "10.619.767/0001-57");
		LojaNovoDTO lj3 = new LojaNovoDTO("Carrefour", "48.290.709/0001-03");
		LojaNovoDTO lj4 = new LojaNovoDTO("Carrefour", "48.290.709/0001-03");
		LojaNovoDTO lj5 = new LojaNovoDTO("Carrefour", "48.290.709/0001-03");
		
		lojas.add(lj1);
		lojas.add(lj2);
		lojas.add(lj3);
		lojas.add(lj4);
		lojas.add(lj5);
		
		return lojas;
	}
}
