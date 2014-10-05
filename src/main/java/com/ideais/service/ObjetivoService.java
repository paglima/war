package com.ideais.service;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideais.dados.Objetivo;
import com.ideais.dao.ObjetivoDao;

@Service
public class ObjetivoService {
	
	private static final int NUMERO_DE_OBJETIVOS = 14;
	
	@Autowired
	private ObjetivoDao objetivoDao;
	
	public Objetivo sorteiaObjetivo() {
		Random random = new Random();
		Integer idObjetivoSorteado = random.nextInt(NUMERO_DE_OBJETIVOS) + 1;
		return objetivoDao.findById(idObjetivoSorteado.longValue());
	}

}