package com.war.dados;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TERRITORIO")
public class Territorio {
	
	@Id
	@Column(name = "ID_TERRITORIO", nullable = false, unique = true)
	private Long idTerritorio;
	
	@Column(name = "NM_TERRITORIO", nullable = false, unique = true)
	private String nomeTerritorio;
	
	@Column(name = "NR_EXERCITO", nullable = false)
	private Integer quantidadeExercito;
	
	@ManyToMany
    @JoinTable(name="VIZINHO", joinColumns=
    {@JoinColumn(name="ID_TERRITORIO")}, inverseJoinColumns=
   	{@JoinColumn(name="ID_TERRITORIO_VIZINHO")})
	private List<Territorio> vizinhos;
	
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

	public Integer getQuantidadeExercito() {
		return quantidadeExercito;
	}

	public void setQuantidadeExercito(Integer quantidadeExercito) {
		this.quantidadeExercito = quantidadeExercito;
	}

	public List<Territorio> getVizinhos() {
		return null;
	}

	public void setVizinhos(List<Territorio> vizinhos) {
		this.vizinhos = vizinhos;
	}
	
}