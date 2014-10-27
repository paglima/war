package com.war.dados;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CONTINENTE")
public class Continente {
	
	@Id
	@Column(name = "ID_CONTINENTE", nullable = false, unique = true)
	private Long idContinente;
	
	@Column(name = "NM_NOME_USUARIO", nullable = false, unique = true)
	private String nomeContinente;
	
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Territorio.class)
	@JoinColumn(name = "ID_CONTINENTE", nullable = true)
	private List<Territorio> territorios;

	public Long getIdContinente() {
		return idContinente;
	}

	public void setIdContinente(Long idContinente) {
		this.idContinente = idContinente;
	}

	public String getNomeContinente() {
		return nomeContinente;
	}

	public void setNomeContinente(String nomeContinente) {
		this.nomeContinente = nomeContinente;
	}

	public List<Territorio> getTerritorios() {
		if (territorios == null) {
			territorios = new ArrayList<Territorio>();
		}
		
		return territorios;
	}

	public void setTerritorios(List<Territorio> territorios) {
		this.territorios = territorios;
	}
	
}