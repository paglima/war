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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "USUARIO")
public class Usuario {
	
	@Id
	@SequenceGenerator( name = "sequence", sequenceName = "USUARIO_SEQ_ID" )
    @GeneratedValue( generator = "sequence", strategy = GenerationType.AUTO )
	@Column(name = "ID_USUARIO", nullable = false, unique = true)
	private Long idUsuario;
	
	@Column(name = "NM_NOME", nullable = false)
	private String nomeUsuario;
	
	@Column(name = "NM_COR", nullable = false)
	private String cor;
	
	@Column(name = "BO_HUMANO", nullable = false)
	private Boolean jogadorHumano = Boolean.FALSE;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_OBJETIVO", nullable = true)
	private Objetivo objetivo;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USUARIO", nullable = true)
	private List<Territorio> territorios;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USUARIO", nullable = true)
	private List<Carta> cartas;
	
	@ManyToOne
	@JoinColumn(name="ID_JOGO", referencedColumnName="ID_JOGO", nullable=false)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Jogo jogo;
	
	@Transient
	private Boolean turnoDaJogada = Boolean.TRUE;
	
	@Transient
	private Integer exercitoSobrando;
	
	@Transient
	private Boolean aindaNoJogo = Boolean.TRUE;
	
	@Transient
	private Boolean iniciador = Boolean.TRUE;
	
	@Transient
	private Boolean jaPegouCartaNoTurno = Boolean.TRUE;
	
	@Transient
	private Boolean conquistouTerritorio = Boolean.FALSE;
	
	public Usuario() {
		territorios = new ArrayList<Territorio>();
	}
	
	public Usuario(String nome) {
		nomeUsuario = nome;
		territorios = new ArrayList<Territorio>();
	}
	
	public Usuario(String nome, String nomeCor) {
		nomeUsuario = nome;
		cor = nomeCor;
		jogadorHumano = Boolean.TRUE;
		turnoDaJogada = Boolean.TRUE;
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

	public void addTerritorio(Territorio territorio) {
		if(territorios == null) {
			territorios = new ArrayList<Territorio>();
		}
		
		territorios.add(territorio);
	}
	
	public void addCarta(Carta carta) {
		if(cartas == null) {
			cartas = new ArrayList<Carta>();
		}
		
		cartas.add(carta);
	}

	public List<Territorio> getTerritorios() {
		return territorios;
	}

	public void setTerritorios(List<Territorio> territorios) {
		this.territorios = territorios;
	}

	public Boolean getJogadorHumano() {
		return jogadorHumano;
	}

	public void setJogadorHumano(Boolean jogadorHumano) {
		this.jogadorHumano = jogadorHumano;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public List<Carta> getCartas() {
		if (cartas == null) {
			cartas = new ArrayList<Carta>();
		}
		
		return cartas;
	}

	public void setCartas(List<Carta> cartas) {
		this.cartas = cartas;
	}

	public Boolean getTurnoDaJogada() {
		return turnoDaJogada;
	}

	public void setTurnoDaJogada(Boolean turnoDaJogada) {
		this.turnoDaJogada = turnoDaJogada;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public Integer getExercitoSobrando() {
		return exercitoSobrando;
	}

	public void setExercitoSobrando(Integer exercitoSobrando) {
		this.exercitoSobrando = exercitoSobrando;
	}
	
	public Integer getTotalDeTerritorios() {
		if (territorios == null) {
			territorios = new ArrayList<Territorio>();
		}
		
		return territorios.size();
	}

	public boolean vazio() {
		return (cor == null || nomeUsuario == null || jogo == null);
	}

	public Boolean getAindaNoJogo() {
		return aindaNoJogo;
	}

	public void setAindaNoJogo(Boolean aindaNoJogo) {
		this.aindaNoJogo = aindaNoJogo;
	}

	public Territorio getTerritorioComMaiorExercitoParaAtaque() {
		Territorio maiorExercito = getTerritorios().get(0);
		
		for (Territorio territorio : getTerritorios()) {
			if (territorio.getQuantidadeExercito() > maiorExercito.getQuantidadeExercito() && territorio.contemVizinhoInimigo()) {
				maiorExercito = territorio;
			}
		}
		
		if (maiorExercito.getQuantidadeExercito() == 1) {
			return null;
		}
		
		return maiorExercito;
	}

	public Territorio getTerritorioASerAtacado(Territorio atacante) {
		if (atacante == null) {
			return null;
		}
		
		Territorio menorExercito = atacante.getVizinhos().get(0);
		
		for (Territorio vizinho : atacante.getVizinhos()) {
			if ((vizinho.getQuantidadeExercito()) <= (menorExercito.getQuantidadeExercito()) 
				&& !cor.equals(vizinho.getUsuario().getCor()) && (vizinho.getQuantidadeExercito() > 0)) {
				menorExercito = vizinho;
			} else if (menorExercito.getUsuario().equals(cor)) {
				menorExercito = vizinho;
			}
		}
		
		if (menorExercito.getIdTerritorio().equals(atacante.getVizinhos().get(0).getIdTerritorio()) && 
		   (((menorExercito.getQuantidadeExercito() == 0)) || 
		    menorExercito.getUsuario().getCor().equals(cor))) {
			return null;
		}
		
		return menorExercito;
	}

	public void removeTerritorio(Territorio defensor) {
		for (int i = 0; i < getTerritorios().size(); i++) {
			if (getTerritorios().get(i).getIdTerritorio().equals(defensor.getIdTerritorio())) {
				getTerritorios().remove(i);
			}
		}
	}

	public Boolean getIniciador() {
		return iniciador;
	}

	public void setIniciador(Boolean iniciador) {
		this.iniciador = iniciador;
	}

	public boolean contemTerritorio(Territorio territorio) {
		for (Territorio territorioDoUsuario : getTerritorios()) {
			if (territorioDoUsuario.getIdTerritorio().equals(territorio.getIdTerritorio())) {
				return true;
			}
		}
		
		return false;
	}

	public Boolean getJaPegouCartaNoTurno() {
		return jaPegouCartaNoTurno;
	}

	public void setJaPegouCartaNoTurno(Boolean jaPegouCartaNoTurno) {
		this.jaPegouCartaNoTurno = jaPegouCartaNoTurno;
	}

	public Boolean getConquistouTerritorio() {
		return conquistouTerritorio;
	}

	public void setConquistouTerritorio(Boolean conquistouTerritorio) {
		this.conquistouTerritorio = conquistouTerritorio;
	}

}