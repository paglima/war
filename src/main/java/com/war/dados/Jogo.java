package com.war.dados;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "JOGO")
public class Jogo {
	
	@Id
	@SequenceGenerator( name = "sequence", sequenceName = "JOGO_SEQ_ID" )
    @GeneratedValue( generator = "sequence", strategy = GenerationType.AUTO )
	@Column(name = "ID_JOGO", nullable = false, unique = true)
	private Long idJogo;
	
	@Column(name = "NM_NOME", nullable = false, unique = true)	
	private String nome;
	
	@Column(name = "NM_TIPO", nullable = false)
	private String tipo;
	
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Usuario.class)
	@JoinColumn(name = "ID_JOGO", nullable = true)
	private List<Usuario> usuarios;
	
	public Jogo(String tipo, Usuario usuario, Integer quantidadeInimigos, String nomeIdentificador) {
		this.tipo = tipo;
		nome = nomeIdentificador;
		getUsuarios().add(usuario);
		
		for (int i = 1; i <= quantidadeInimigos; i++) {
			usuarios.add(new Usuario("Inimigo " + i));
		}
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

	public Long getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(Long idJogo) {
		this.idJogo = idJogo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}