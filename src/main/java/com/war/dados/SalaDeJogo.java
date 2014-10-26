package com.war.dados;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class SalaDeJogo {
	
	private List<Jogo> jogos;

	public List<Jogo> getJogos() {
		if (jogos == null) {
			jogos = new ArrayList<Jogo>();
		}
		
		return jogos;
	}

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}
	
	public void adicionaJogo(Jogo jogo) {
		getJogos().add(jogo);
	}
	
	public Jogo getJogoPorId(Long id) {
		for (Jogo jogo : jogos) {
			if (jogo.getId().equals(id)) {
				return jogo;
			}
		}
		
		return null;
	}
	
	public Jogo getJogoPorNome(String nome) {
		for (Jogo jogo : jogos) {
			if (jogo.getNome().equals(nome)) {
				return jogo;
			}
		}
		
		return null;
	}
	
}