package com.war.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.dados.Continente;
import com.war.dados.Cor;
import com.war.dados.Jogo;
import com.war.dados.Objetivo;
import com.war.dados.TipoDeJogo;
import com.war.dados.Usuario;

public class JogoServiceTest extends JogoService{
	
	Jogo jogo;
	Usuario usuario ;		
	ContinenteDaoMock continenteDao;
	List<Continente> continentes;	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		continenteDao = new ContinenteDaoMock();
		usuario = new Usuario();	
		usuario.setCor(Cor.AMARELO.getNomeCor());		
		continentes = continenteDao.findAll();
		jogo = new Jogo(TipoDeJogo.SOLO.getTipo(), usuario, 0, "TESTE");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void verificaSeGanhou3ContinentesTest() {				
		
		usuario.getTerritorios().addAll(Continente.getContinenteByNome(continentes, "Europa").getTerritorios());
		usuario.getTerritorios().addAll(Continente.getContinenteByNome(continentes, "Oceania").getTerritorios());
		usuario.getTerritorios().addAll(Continente.getContinenteByNome(continentes, "Africa").getTerritorios());
		Objetivo objetivo = new Objetivo();
		objetivo.setIdObjetivo(1l);
		usuario.setObjetivo(objetivo);			
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(usuario);		
		jogo.setUsuarios(usuarios);
		assertEquals(jogo.verificaFim(continentes).getCor() , usuario.getCor());		
	}
	
	@Test
	public void verificaSeGanho24ContinentesTest() {						
		usuario.getTerritorios().addAll(Continente.getContinenteByNome(continentes, "Europa").getTerritorios());
		usuario.getTerritorios().addAll(Continente.getContinenteByNome(continentes, "Oceania").getTerritorios());
		usuario.getTerritorios().addAll(Continente.getContinenteByNome(continentes, "Africa").getTerritorios());
		usuario.getTerritorios().addAll(Continente.getContinenteByNome(continentes, "Asia").getTerritorios());
		usuario.getTerritorios().addAll(Continente.getContinenteByNome(continentes, "America do sul").getTerritorios());
		Objetivo objetivo = new Objetivo();
		objetivo.setIdObjetivo(7l);
		usuario.setObjetivo(objetivo);			
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(usuario);		
		jogo.setUsuarios(usuarios);
		assertEquals(jogo.verificaFim(continentes).getCor() , usuario.getCor());		
	}
	
	@Test
	public void verificaSeExercitoDerrotadoContinentesTest() {						
		Objetivo objetivo = new Objetivo();
		objetivo.setIdObjetivo(9l);
		usuario.setObjetivo(objetivo);	
		Usuario usuario2 = new Usuario();
		usuario2.setCor(Cor.AZUL.getNomeCor());
		usuario2.setAindaNoJogo(false);
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(usuario);		
		usuarios.add(usuario2);
		jogo.setUsuarios(usuarios);
		assertEquals(jogo.verificaFim(continentes).getCor() , usuario.getCor());		
	}

}