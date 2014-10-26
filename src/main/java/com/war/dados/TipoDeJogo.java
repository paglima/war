package com.war.dados;

public enum TipoDeJogo {
	
	SOLO("SOLO"), 
	MULTIJOGADOR("MULTI-JOGADOR");

	private final String tipo;

	TipoDeJogo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}
	
}