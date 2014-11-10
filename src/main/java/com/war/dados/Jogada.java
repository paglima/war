package com.war.dados;

import java.util.ArrayList;
import java.util.List;

public class Jogada {
	
	private List<Ataque> ataques;
	private List<String> sumarioJogada;

	public List<Ataque> getAtaques() {
		if (ataques == null) {
			ataques = new ArrayList<Ataque>();
		}
		
		return ataques;
	}

	public void setAtaques(List<Ataque> ataques) {
		this.ataques = ataques;
	}
	
	public List<String> getSumarioJogada() {
		if (sumarioJogada == null) {
			sumarioJogada = new ArrayList<String>();
		}
		
		return sumarioJogada;
	}

	public void setSumarioJogada(List<String> sumarioJogada) {
		this.sumarioJogada = sumarioJogada;
	}

	public boolean naoContemDefensorHumano() {
		for (Ataque ataque : getAtaques()) {
			if (ataque.getDefensor().getUsuario().getJogadorHumano()) {
				return false;
			}
		}
		
		return true;
	}

	public Ataque getUltimoAtaque() {
		return getAtaques().get(ataques.size() - 1);
	}

	public void processaAtaque(Ataque ataque) {
		Integer quantidadeDadosAtaque = pegaDadosParaAtaque(ataque.getAtacante().getQuantidadeExercito() - ataque.getAtacante().getExercitosAPerder());
		Integer quantidadeDadosDefesa = pegaDadosParaDefesa(ataque.getDefensor().getQuantidadeExercito() - ataque.getDefensor().getExercitosAPerder());
		
		if (quantidadeDadosAtaque == 3 & quantidadeDadosDefesa == 3) {
			ataqueComDadosIguais(ataque, quantidadeDadosDefesa);
			
		} else if (quantidadeDadosDefesa <= 2 && quantidadeDadosAtaque == 3) {
			ataqueComAtacanteComMaisDados(ataque, quantidadeDadosAtaque, quantidadeDadosDefesa);
			
		} else {
			quantidadeDadosDefesa = quantidadeDadosAtaque;
			ataqueComDadosIguais(ataque, quantidadeDadosDefesa);
		}
	}

	private void ataqueComAtacanteComMaisDados(Ataque ataque, Integer quantidadeDadosAtaque, Integer quantidadeDadosDefesa) {
		ArrayList<Integer> jogadasDeAtaque = geraJogadasMaiores(quantidadeDadosAtaque, quantidadeDadosDefesa);
		
		for (int i = 0; i < quantidadeDadosDefesa; i++) {
			Integer dadoDefesa = (int) (Math.random() * (6 - 1) + 1);
			
			confereResultadoAtaque(ataque, jogadasDeAtaque.get(i), dadoDefesa);
		}
	}
	
	private void ataqueComDadosIguais(Ataque ataque, Integer quantidadeDadosDefesa) {
		for (int i = 0; i < quantidadeDadosDefesa; i++) {
			Integer dadoAtaque = (int) (Math.random() * (6 - 1) + 1);
			Integer dadoDefesa = (int) (Math.random() * (6 - 1) + 1);
			
			confereResultadoAtaque(ataque, dadoAtaque, dadoDefesa); 
		}
	}

	private void confereResultadoAtaque(Ataque ataque, Integer dadoAtaque, Integer dadoDefesa) {
		if (dadoAtaque > dadoDefesa) {
			this.getSumarioJogada().add(processaMensagemDeMovimento(ataque.getDefensor(), ataque.getAtacante()));
			ataque.aumentaExercitoPerdidosPorDefesa(1);
			ataque.getDefensor().aumentaExercitosAPerder(1);
			
		} else {
			this.getSumarioJogada().add(processaMensagemDeMovimento(ataque.getAtacante(), ataque.getDefensor()));
			ataque.aumentaExercitoPerdidosPorAtaque(1);
			ataque.getAtacante().aumentaExercitosAPerder(1);
		}		
	}

	private String processaMensagemDeMovimento(Territorio perdedor, Territorio vencedor) {
		return perdedor.getNomeTerritorio() + " perdeu 1 exército para " + vencedor.getNomeTerritorio() +  ".";
	}

	private ArrayList<Integer> geraJogadasMaiores(Integer dadosMaiorQuantidade, Integer dadosMenorQuantidade) {
		ArrayList<Integer> todosOsDadosLancados = new ArrayList<Integer>();
		ArrayList<Integer> dadosDeRetorno = new ArrayList<Integer>();
		
		for (int i = 0; i < dadosMaiorQuantidade; i++) {
			todosOsDadosLancados.add((int) (Math.random() * (6 - 1) + 1));
		}
		
		for (int i = 0; i < dadosMenorQuantidade; i++) {
			dadosDeRetorno.add(pegaMelhorDado(todosOsDadosLancados));
		}
		
		return dadosDeRetorno;
	}

	private Integer pegaMelhorDado(ArrayList<Integer> dados) {
		Integer indiceDoMaior = 0;
		
		for (int i = 0; i < dados.size(); i++) {
			if (dados.get(i) > dados.get(indiceDoMaior)) {
				indiceDoMaior = i;
			}
		}
		
		Integer melhorValor = dados.get(indiceDoMaior);
		dados.remove(indiceDoMaior);
		
		return melhorValor;
	}

	private Integer pegaDadosParaDefesa(Integer quantidadeExercitos) {
		if (quantidadeExercitos > 3) {
			return 3;
		}
		
		return quantidadeExercitos;
	}

	private Integer pegaDadosParaAtaque(Integer quantidadeExercitos) {
		if (quantidadeExercitos > 3) { 
			return 3;
		} 
		if (quantidadeExercitos == 2) {
			return 1;
		}
	
		return 2;
	}
	
}