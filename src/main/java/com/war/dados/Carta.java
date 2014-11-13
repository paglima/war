package com.war.dados;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "CARTA")
public class Carta {

	@Id
	@Column(name = "ID_CARTA", nullable = false, unique = true)
	private Long idCarta;
	
	@Column(name = "NM_SIMBOLO", nullable = false, unique = true)
	private String simbolo;
	
	@Column(name = "BO_CORINGA", nullable = false)
	private Boolean cartaCoringa;
	
	@OneToOne
	@JoinColumn(name="ID_CARTA", referencedColumnName="ID_CARTA", nullable=true)
	private Territorio Territorio;
	
	@Transient
	private static Integer trocaAtual = 0;
	
	@Transient
	private Boolean usada = Boolean.FALSE;

	public Long getIdCarta() {
		return idCarta;
	}

	public void setIdCarta(Long idCarta) {
		this.idCarta = idCarta;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public Boolean getCartaCoringa() {
		return cartaCoringa;
	}

	public void setCartaCoringa(Boolean cartaCoringa) {
		this.cartaCoringa = cartaCoringa;
	}

	public Territorio getTerritorio() {
		return Territorio;
	}

	public void setTerritorio(Territorio territorio) {
		Territorio = territorio;
	}

	public Boolean getUsada() {
		return usada;
	}

	public void setUsada(Boolean usada) {
		this.usada = usada;
	}
	
	public static Integer geraExercitoDeTroca(Integer troca) {
		if (troca != null) {
			if (troca == 1) {
				trocaAtual = 4;
			} else if (troca == 2) {
				trocaAtual = 6;
			} else if (troca == 3) {
				trocaAtual = 8;
			} else if (troca == 4) {
				trocaAtual = 10;
			} else if (troca == 5) {
				trocaAtual = 12;
			} else if (troca == 6) {
				trocaAtual = 15;
			} else {
				trocaAtual += 5;
			}
		}
		
		return trocaAtual;
	}
	
}