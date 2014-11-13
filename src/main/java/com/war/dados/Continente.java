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
	
	@Column(name = "NM_NOME", nullable = false, unique = true)
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

	public boolean verificaSeContinenteFoiConquistado(Usuario usuario) {
		for (Territorio territorio : this.getTerritorios()) {
			if (!usuario.contemTerritorio(territorio)) {
				return false;
			}
		}
		
		return true;
	}
	
	public static Continente getContinenteByNome(List<Continente> continentes, String nomeContinente) {
		for (Continente continente : continentes) {
			if (nomeContinente.equals(continente.getNomeContinente())) {
				return continente;
			}
		}
		
		return null;
	}
	
	public static Integer retornaExercitoPorContinente(String nomeContinente) {
		if (nomeContinente != null && !"".equals(nomeContinente)) {
			if (nomeContinente.equals("America do Sul")) return 2;
			if (nomeContinente.equals("America do Norte")) return 5;
			if (nomeContinente.equals("Europa")) return 5;
			if (nomeContinente.equals("Asia")) return 7;
			if (nomeContinente.equals("Africa")) return 3;
			if (nomeContinente.equals("Oceania")) return 2;
		}
		
		return 0;
	}
	
}