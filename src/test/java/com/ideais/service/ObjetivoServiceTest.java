package com.ideais.service;

import org.springframework.beans.factory.annotation.Autowired;

import junit.framework.TestCase;

public class ObjetivoServiceTest extends TestCase{
	
	@Autowired
	private ObjetivoService objetivoService;
	
	public void test_sorteia_objetivo(){
		objetivoService.sorteiaObjetivo();
	}

}
