package com.ideais.dados;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TERRITORIO")
public class Territorio {
	
	@Id
	@Column(name = "ID_TERRITORIO", nullable = false, unique = true)
	private Long idTerritorio;
	
	@Column(name = "NM_TERRITORIO", nullable = false, unique = true)
	private String nomeTerritorio;

	public Territorio(){
		
	}
	
	public String getNomeTerritorio() {
		return nomeTerritorio;
	}

	public void setNomeTerritorio(String nomeTerritorio) {
		this.nomeTerritorio = nomeTerritorio;
	}

	public Long getIdTerritorio() {
		return idTerritorio;
	}

	public void setIdTerritorio(Long idTerritorio) {
		this.idTerritorio = idTerritorio;
	}

}
