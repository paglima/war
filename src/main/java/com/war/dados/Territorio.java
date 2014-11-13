package com.war.dados;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "TERRITORIO")
public class Territorio {
	
	@Id
	@Column(name = "ID_TERRITORIO", nullable = false, unique = true)
	private Long idTerritorio;
	
	@Column(name = "NM_NOME", nullable = false, unique = true)
	private String nomeTerritorio;
	
	@Column(name = "NR_NUM_PECAS", nullable = false)
	private Integer quantidadeExercito;
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="VIZINHO",
	 joinColumns=@JoinColumn(name="ID_TERRITORIO"),
	 inverseJoinColumns=@JoinColumn(name="ID_TERRITORIO_VIZINHO"))
	private List<Territorio> vizinhos;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)  
	@JoinTable(name="VIZINHO",
	 joinColumns=@JoinColumn(name="ID_TERRITORIO_VIZINHO"),
	 inverseJoinColumns=@JoinColumn(name="ID_TERRITORIO")
	)
	private List<Territorio> vizinhosDe;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_USUARIO", referencedColumnName="ID_USUARIO", nullable=false)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="ID_CONTINENTE", referencedColumnName="ID_CONTINENTE", nullable=false)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Continente continente;
	
	@Transient
	private String corDoConquistador = "";
	
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Territorio> getVizinhos() {
		if (vizinhos == null) {
			vizinhos = new ArrayList<Territorio>();
		}
		
		return vizinhos;
	}

	public void setVizinhos(List<Territorio> vizinhos) {
		this.vizinhos = vizinhos;
	}

	public List<Territorio> getVizinhosDe() {
		if (vizinhosDe == null) {
			vizinhosDe = new ArrayList<Territorio>();
		}
		
		return vizinhosDe;
	}

	public void setVizinhosDe(List<Territorio> vizinhosDe) {
		this.vizinhosDe = vizinhosDe;
	}

	@Override
	public String toString() {
		String[] palavrasNoNome = nomeTerritorio.split("-");
		String nome = "";
		
		for (int i = 0; i < palavrasNoNome.length; i++) {
			nome += palavrasNoNome[i] + " ";
		}
		
		return nome.substring(0, nome.length() - 1);
	}

	public void atualizaInfo(Territorio territorio) {
		if (territorio != null) {
			quantidadeExercito = territorio.getQuantidadeExercito();
		}
		
		if (usuario.getTurnoDaJogada()) {
			usuario.setExercitoSobrando(0);
		}
	}
	
	public void atualizaJogadorDonoDoTerritorio(Territorio territorio, String cor) {
		if (territorio != null) {
			Usuario usuarioBuscado = usuario.getJogo().getUsuarioPelaCor(cor);
	
			if (usuarioBuscado != null) {
				usuario.removeTerritorio(this);
				this.setUsuario(usuarioBuscado);
				usuario.addTerritorio(this);
			}
		}
	}
	
	public List<Territorio> getVizinhosJogadorHumano() {
		List<Territorio> vizinhosJogadorHumano = new ArrayList<Territorio>();
		
		for (Territorio vizinho : getVizinhos()) {
			if (vizinho.getUsuario().getJogadorHumano()) {
				vizinhosJogadorHumano.add(vizinho);
			}
		}
		
		return vizinhosJogadorHumano;
	}
	
	public List<Territorio> getVizinhosJogadorHumanoQuePodemAtacar() {
		List<Territorio> vizinhosJogadorHumano = new ArrayList<Territorio>();
		
		for (Territorio vizinho : getVizinhos()) {
			if (vizinho.getUsuario().getJogadorHumano() && vizinho.getQuantidadeExercito() >= 2) {
				vizinhosJogadorHumano.add(vizinho);
			}
		}
		
		return vizinhosJogadorHumano;
	}
	
	public boolean getJogadorHumanoPodeAtacar() {
		if (!usuario.getJogo().getUsuarioDaVez().getJogadorHumano()) {
			return false;
		}
		
		for (Territorio vizinho : getVizinhos()) {
			if (vizinho.getUsuario().getJogadorHumano() && !usuario.getJogadorHumano()) {
				return true;
			}
		}
		
		return false;
	}

	public boolean contemVizinhoInimigo() {
		for (Territorio vizinho : getVizinhos()) {
			if (vizinho.getUsuario().getCor().equals(getUsuario().getCor())) {
				return false;
			}
		}
		
		return true;
	}
	
	public void diminuiExercito(Integer quantidade) {
		this.quantidadeExercito -= quantidade;
	}

	public String getCorDoConquistador() {
		return corDoConquistador;
	}

	public void setCorDoConquistador(String corDoConquistador) {
		this.corDoConquistador = corDoConquistador;
	}

	public Continente getContinente() {
		return continente;
	}

	public void setContinente(Continente continente) {
		this.continente = continente;
	}
	
}