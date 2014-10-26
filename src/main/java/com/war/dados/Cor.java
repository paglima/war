package com.war.dados;

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
	
}