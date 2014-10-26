package com.war.dados;

public enum Cor {
		
	VERMELHO("Vermelho", "#D50000"),
	VERDE("Verde","#009917"),
	AMARELO("Amarelo", "#E9FA00"),
	AZUL("Azul", "#0721B4"),
	LARANJA("Laranja", "#EF9F00"),
	ROXO("Roxo", "#8C00F7"),
	ROSA("Rosa", "#F300C7");
	
	private final String nomeCor;
	private final String codigoCor;
	
	Cor(String nomeCor, String codigoCor) {
		this.nomeCor = nomeCor;
		this.codigoCor = codigoCor;
	}
	
	public String getCodigoCor() {
		return codigoCor;
	}
	
	public String getNomeCor() {
		return nomeCor;
	}
	
}