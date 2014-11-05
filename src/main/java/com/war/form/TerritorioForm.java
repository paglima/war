package com.war.form;

import java.util.ArrayList;
import java.util.List;

import com.war.dados.Territorio;

public class TerritorioForm {
	
	List<Territorio> territorios;
	
	public TerritorioForm() {
		territorios = new ArrayList<Territorio>();
	}

	public List<Territorio> getTerritorios() {
		if (territorios == null) {
			territorios = new ArrayList<Territorio>();
		}
		
		return territorios;
	}

	public void setTerritorios(List<Territorio> territorios) {
		this.territorios = territorios;
	}
	
}