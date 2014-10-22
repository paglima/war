package com.war.dados;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TERRITORIO")
public class Territorio {
	
	@Id
	@Column(name = "ID_TERRITORIO", nullable = false, unique = true)
	private Long idTerritorio;
	
	@Column(name = "NM_TERRITORIO", nullable = false, unique = true)
	private String nomeTerritorio;
	
//	@Column(name = "NR_EXERCITO", nullable = false)
//	private Integer quantidadeExercito;
//	
//	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinColumn(name = "ID_TERRITORIO", nullable = true)
//	private List<Territorio> vizinhos;
	
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

//	public Integer getQuantidadeExercito() {
//		return quantidadeExercito;
//	}
//
//	public void setQuantidadeExercito(Integer quantidadeExercito) {
//		this.quantidadeExercito = quantidadeExercito;
//	}
//
//	public List<Territorio> getVizinhos() {
//		return vizinhos;
//	}
//
//	public void setVizinhos(List<Territorio> vizinhos) {
//		this.vizinhos = vizinhos;
//	}
	
}