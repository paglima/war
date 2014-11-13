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
	private String cartaCoringa;
	
	@OneToOne
	@JoinColumn(name="ID_CARTA", referencedColumnName="ID_CARTA", nullable=true)
	private Territorio Territorio;
	
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

	public String getCartaCoringa() {
		return cartaCoringa;
	}

	public void setCartaCoringa(String cartaCoringa) {
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
	
}