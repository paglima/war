package com.war.dados;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OBJETIVO")
public class Objetivo {

	@Id
	@Column(name = "ID_OBJETIVO", nullable = false, unique = true)
	private Long idObjetivo;
	
	@Column(name = "NM_DESCRICAO", nullable = false)
	private String descricao;

	public Long getIdObjetivo() {
		return idObjetivo;
	}

	public void setIdObjetivo(Long idObjetivo) {
		this.idObjetivo = idObjetivo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}