package com.ideais.dados;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


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
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_OBJETIVO", nullable = false)
	private Objetivo objetivo;
	
	
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
	
	
}
