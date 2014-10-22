package com.war.service;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.war.service.ObjetivoService;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = "classpath:test-context.xml")
public class ObjetivoServiceTest extends TestCase{
	
	@Autowired
	private ObjetivoService objetivoService;
	
	@Test
	public void test_sorteia_objetivo(){
		objetivoService.sorteiaObjetivo();
	}

}