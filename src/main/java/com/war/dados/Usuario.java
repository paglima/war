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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "USUARIO")
public class Usuario {
	
	@Id
	@SequenceGenerator( name = "sequence", sequenceName = "USUARIO_SEQ_ID" )
    @GeneratedValue( generator = "sequence", strategy = GenerationType.AUTO )
	@Column(name = "ID_USUARIO", nullable = false, unique = true)
	private Long idUsuario;
	
	@Column(name = "NM_NOME_USUARIO", nullable = false, unique = true)
	private String nomeUsuario;
	
	@Transient
	private Boolean jogadorUsuario;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_OBJETIVO", nullable = true)
	private Objetivo objetivo;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_USUARIO", nullable = true)
	private List<Territorio> territorios;
	
	public Usuario() {
		territorios = new ArrayList<Territorio>();
	}
	
	public Usuario(String nome) {
		nomeUsuario = nome;
		territorios = new ArrayList<Territorio>();
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Objetivo getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public List<Territorio> getTerritorio() {
		return territorios;
	}

	public void setTerritorio(List<Territorio> territorio) {
		this.territorios = territorio;
	}

	public void addTerritorio(Territorio territorio) {
		if(territorios == null){
			territorios = new ArrayList<Territorio>();
		}
		
		territorios.add(territorio);
	}

	public List<Territorio> getTerritorios() {
		return territorios;
	}

	public void setTerritorios(List<Territorio> territorios) {
		this.territorios = territorios;
	}

	public Boolean getJogadorUsuario() {
		return jogadorUsuario;
	}

	public void setJogadorUsuario(Boolean jogadorUsuario) {
		this.jogadorUsuario = jogadorUsuario;
	}
	
}