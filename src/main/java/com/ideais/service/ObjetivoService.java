package com.ideais.service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ideais.dados.Objetivo;
import com.ideais.dados.Usuario;
import com.ideais.dao.ObjetivoDao;

@Component
public class ObjetivoService{
	
	private static final int NUMERO_DE_OBJETIVOS = 14;
	
	@Autowired
	private ObjetivoDao objetivoDao;
	
	private List<Integer> sorteiaObjetivosId(int numeroUsuarios) {
		Random random 			 = new Random();
		Set<Integer> idSet		 = new HashSet<Integer>();
		
		do{
			Integer idObjetivoSorteado = random.nextInt(NUMERO_DE_OBJETIVOS) + 1;
			idSet.add(idObjetivoSorteado);
		}while(idSet.size() < numeroUsuarios);
		
		return new ArrayList<Integer>(idSet);
	}
	
	public void sorteiaObjetivos(List<Usuario> usuarios){
		int numeroUsuarios 		  = usuarios.size();
		List<Integer> objetivoIds = sorteiaObjetivosId(numeroUsuarios);
		
		for (int i = 0; i < numeroUsuarios; i++) {
			Integer  objetivoId = objetivoIds.get(i);
			Objetivo objetivo   = objetivoDao.findById(objetivoId.longValue());
			
			Usuario usuario = usuarios.get(i);
			usuario.setObjetivo(objetivo);
		}
	}

	public Objetivo findById(Long id){
		return objetivoDao.findById(id);
	}
}