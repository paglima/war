package com.war.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.war.dados.Carta;
import com.war.dao.CartaDao;

@Component
public class CartaService {
	
	
	@Autowired
	private CartaDao cartaDao;
	
	public List<Carta> encontraTodasAsCartas() {
		return cartaDao.findAll();
	}

	public Carta sorteiaCarta(List<Carta> cartas) {
		Integer indiceRandomico = (int) (Math.random() * (cartas.size()));
		
		while (cartas.get(indiceRandomico).getUsada()) {
			indiceRandomico = (int) (Math.random() * (cartas.size()));
		}
		
		cartas.get(indiceRandomico).setUsada(Boolean.TRUE);
		
		return cartas.get(indiceRandomico);
	}
	
}