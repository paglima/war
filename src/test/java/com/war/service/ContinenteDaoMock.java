package com.war.service;

import java.util.ArrayList;
import java.util.List;

import com.war.dados.Continente;
import com.war.dados.Territorio;

public class ContinenteDaoMock {
	
	
	public List<Continente> findAll() {
		Continente continente; 
		List<Continente> list = new ArrayList<Continente>();
		List<Territorio> listT = new ArrayList<Territorio>();				
		
		Territorio t = new Territorio();
		t.setNomeTerritorio("Brasil");
		t.setIdTerritorio(3094l);
		listT.add(t);		
		
		t = new Territorio();
		t.setNomeTerritorio("Argentina-Uruguai");
		t.setIdTerritorio(309433l);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Chile-Bolivia-Peru");
		t.setIdTerritorio(309441l);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Colombia-Venezuela");
		t.setIdTerritorio(3091554l);
		listT.add(t);
		continente = new Continente();
		continente.setNomeContinente("America do sul");		
		continente.setTerritorios(listT);
		t.setIdTerritorio(309l);
		list.add(continente);
		listT = new ArrayList();
		
		t = new Territorio();
		t.setNomeTerritorio("Mexico");
		listT.add(t);
		t.setIdTerritorio(3l);
		t = new Territorio();
		t.setNomeTerritorio("Nova-York");
		t.setIdTerritorio(3093331112l);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("California");
		t.setIdTerritorio(9872488l);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Ottawa");
		t.setIdTerritorio(1414l);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Vancouver");
		t.setIdTerritorio(4920l);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Labrador");
		t.setIdTerritorio(14442l);
		listT.add(t);
		t = new Territorio();		
		t.setNomeTerritorio("Alaska");
		t.setIdTerritorio(1344422l);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Mackenzie");
		t.setIdTerritorio(124124124l);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Groelandia");
		t.setIdTerritorio(51313l);
		listT.add(t);		
		continente = new Continente();
		continente.setNomeContinente("America do norte");		
		continente.setTerritorios(listT);
		list.add(continente);
		
		listT = new ArrayList();
		t = new Territorio();
		t.setNomeTerritorio("Islandia");
		t.setIdTerritorio(6521313l);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Inglaterra");
		t.setIdTerritorio(67890042901l);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Suecia");
		t.setIdTerritorio(12412555111l);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Moscou");
		t.setIdTerritorio(12124121l);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Polonia-Iugoslavia");
		t.setIdTerritorio(14121241240000l);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Portugal-Espanha-Franca");
		t.setIdTerritorio(12412412421l);
		listT.add(t);
		t = new Territorio();
		t.setIdTerritorio(88585855l);
		t.setNomeTerritorio("Alemanha");
		listT.add(t);		
		continente = new Continente();
		continente.setNomeContinente("Europa");		
		t.setIdTerritorio(4202949l);
		continente.setTerritorios(listT);
		list.add(continente);
		listT = new ArrayList();
		
		t = new Territorio();
		t.setNomeTerritorio("Argelia-Nigeria");
		t.setIdTerritorio(013013l);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Egito");
		t.setIdTerritorio(1112223332l);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Sudao");
		t.setIdTerritorio(9991110l);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Congo");
		t.setIdTerritorio(11l);		
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Africa-do-Sul");
		t.setIdTerritorio(3l);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Madagascar");
		t.setIdTerritorio(4l);
		listT.add(t);
		continente = new Continente();
		continente.setNomeContinente("Africa");		
		t.setIdTerritorio(1111111111l);
		continente.setTerritorios(listT);
		list.add(continente);		
		listT = new ArrayList();
				
		t = new Territorio();
		t.setNomeTerritorio("Oriente-Medio");
		t.setIdTerritorio((long) Math.random()* 10000);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Aral");
		t.setIdTerritorio((long) Math.random()* 10000);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Omsk");
		t.setIdTerritorio((long) Math.random()* 10000);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Dudinka");		
		t.setIdTerritorio((long) Math.random()* 10000);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Siberia");
		t.setIdTerritorio((long) Math.random()* 10000);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Tchita");
		t.setIdTerritorio((long) Math.random()* 10000);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Mongolia");
		t.setIdTerritorio((long) Math.random()* 10000);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("China");
		t.setIdTerritorio((long) Math.random()* 10000);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("India");
		t.setIdTerritorio((long) Math.random()* 10000);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Vietna");
		t.setIdTerritorio((long) Math.random()* 10000);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Japao");
		t.setIdTerritorio((long) Math.random()* 10000);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Vladivostok");
		t.setIdTerritorio((long) Math.random()* 10000);
		listT.add(t);
						
		continente = new Continente();
		
		continente.setNomeContinente("Asia");		
		continente.setTerritorios(listT);
		list.add(continente);
		listT = new ArrayList();
		t = new Territorio();
		t.setNomeTerritorio("Sumatra");
		t.setIdTerritorio((long) Math.random()* 10000000);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Borneo");
		t.setIdTerritorio((long) Math.random()* 10000000);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Nova-Guine");
		t.setIdTerritorio((long) Math.random()* 10000000);
		listT.add(t);
		t = new Territorio();
		t.setNomeTerritorio("Australia");
		t.setIdTerritorio((long) Math.random()* 10000000);
		listT.add(t);
		continente = new Continente();
		continente.setNomeContinente("Oceania");		
		continente.setTerritorios(listT);
		list.add(continente);
		return list;
	}

}