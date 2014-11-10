package com.war.dados;

public class Ataque {
	
	private Territorio atacante;
	private Territorio defensor;
	private Integer exercitoPerdidosPorAtaque = 0;
	private Integer exercitoPerdidosPorDefesa = 0;
	
	public Ataque(Territorio atacante, Territorio defensor) {
		this.atacante = atacante;
		this.defensor = defensor;
	}
	
	public Territorio getAtacante() {
		return atacante;
	}
	
	public void setAtacante(Territorio atacante) {
		this.atacante = atacante;
	}
	
	public Territorio getDefensor() {
		return defensor;
	}

	public void setDefensor(Territorio defensor) {
		this.defensor = defensor;
	}

	public Integer getExercitoPerdidosPorAtaque() {
		return exercitoPerdidosPorAtaque;
	}

	public void setExercitoPerdidosPorAtaque(Integer exercitoPerdidosPorAtaque) {
		this.exercitoPerdidosPorAtaque = exercitoPerdidosPorAtaque;
	}

	public Integer getExercitoPerdidosPorDefesa() {
		return exercitoPerdidosPorDefesa;
	}

	public void setExercitoPerdidosPorDefesa(Integer exercitoPerdidosPorDefesa) {
		this.exercitoPerdidosPorDefesa = exercitoPerdidosPorDefesa;
	}
	
	public void aumentaExercitoPerdidosPorDefesa(Integer quantidade) {
		this.exercitoPerdidosPorDefesa += quantidade;
	}
	
	public void aumentaExercitoPerdidosPorAtaque(Integer quantidade) {
		this.exercitoPerdidosPorAtaque += quantidade;
	}
	
}