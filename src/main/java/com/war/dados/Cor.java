package com.war.dados;

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
	
	public static void removeCor(List<Cor> cores, String usuarioNomeCor) {
		for (Cor cor : cores) {
			if (cor.getNomeCor().equals(usuarioNomeCor)) {
				cores.remove(cor);
				break;
			}
		}
	}
	
}