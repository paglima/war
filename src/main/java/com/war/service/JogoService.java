package com.war.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.war.dados.Territorio;
import com.war.dados.Usuario;
import com.war.dao.TerritorioDao;

@Service
public class JogoService {
	
	@Autowired
	private TerritorioDao territorioDao;

	public void ataque(Territorio territorioAtacante,
			Territorio territorioDefensor) {
		// TODO Auto-generated method stub
		
	}

	public Boolean verificaFimDoJogo() {
		// TODO Auto-generated method stub
		return null;
	}

	public void processaFimDaJogada() {
		// TODO Auto-generated method stub
		
	}

	public void distribuiCarta() {
		// TODO Auto-generated method stub
		
	}

	public void redistribuiExercitos() {
		// TODO Auto-generated method stub
		
	}
	
	public void distribuiTerritorio(List<Usuario> usuarios) {
		List<Territorio> territorios = territorioDao.findAll();
		Collections.shuffle(territorios);
		
		Integer indice = 0;
		
		for (Territorio territorio : territorios) {
			if (indice >= usuarios.size()) {
				indice = 0;
			}
			
			Usuario usuario = usuarios.get(indice);
			if (usuario != null) {
				usuario.addTerritorio(territorio);;
			}
			
			indice++;
		}
	}
	
}