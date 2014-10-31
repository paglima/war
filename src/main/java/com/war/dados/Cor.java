package com.war.dados;

import java.util.ArrayList;
import java.util.List;

public enum Cor {
		
	VERMELHO("Vermelho"),
	VERDE("Verde"),
	AMARELO("Amarelo"),
	AZUL("Azul"),
	LARANJA("Laranja"),
	ROXO("Roxo"),
	ROSA("Rosa");
	
	private final String nomeCor;
	
	Cor(String nomeCor) {
		this.nomeCor = nomeCor;
	}
	
	public String getNomeCor() {
		return nomeCor;
	}
	
	public static List<Cor> removeCor(List<Cor> cores, String usuarioNomeCor) {
		List<Cor> coresSemCorUsuario = new ArrayList<Cor>();
		
		for (Cor cor : cores) {
			if (!cor.getNomeCor().equals(usuarioNomeCor)) {
				coresSemCorUsuario.add(cor);
			}
		}
		
		return coresSemCorUsuario;
	}
	
}