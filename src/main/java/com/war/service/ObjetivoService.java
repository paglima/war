package com.war.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.war.dados.Objetivo;
import com.war.dados.Usuario;
import com.war.dao.ObjetivoDao;

@Component
public class ObjetivoService {
	
	private static final int NUMERO_DE_OBJETIVOS = 14;
	
	@Autowired
	private ObjetivoDao objetivoDao;
	
	private List<Integer> sorteiaObjetivosId(int numeroUsuarios) {
		Set<Integer> idSet = new HashSet<Integer>();
		
		do {
			Integer idObjetivoSorteado = (int) (Math.random() * (NUMERO_DE_OBJETIVOS - 1) + 1);

			idSet.add(idObjetivoSorteado);
		} while (idSet.size() < numeroUsuarios);
		
		return new ArrayList<Integer>(idSet);
	}
	
	public void sorteiaObjetivos(List<Usuario> usuarios) { 
		int numeroUsuarios = usuarios.size();
		List<Integer> objetivoIds = sorteiaObjetivosId(numeroUsuarios);
		
		for (int i = 0; i < numeroUsuarios; i++) {
			Integer  objetivoId = objetivoIds.get(i);
			Objetivo objetivo = objetivoDao.findById(objetivoId.longValue());
			
			Usuario usuario = usuarios.get(i);
			usuario.setObjetivo(objetivo);
		}
	}

	public Objetivo findById(Long id){
		return objetivoDao.findById(id);
	}
	
}