package com.war.dados;

import java.util.ArrayList;
import java.util.List;

public class Jogo {
	
	private Long id;
	private String nome;
	private List<Usuario> usuarios;
	private String tipo;
	
	public Jogo(String tipo, Usuario usuario, Integer quantidadeInimigos, String nomeIdentificador) {
		this.tipo = tipo;
		nome = nomeIdentificador;
		
		getUsuarios().add(usuario);
		
		for (int i = 1; i <= quantidadeInimigos; i++) {
			usuarios.add(new Usuario("Inimigo " + i));
		}
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Usuario> getUsuarios() {
		if (usuarios == null) {
			usuarios = new ArrayList<Usuario>();
		}
		
		return usuarios;
	}
	
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}