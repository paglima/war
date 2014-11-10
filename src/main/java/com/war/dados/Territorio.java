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
	
	@Transient
	private Integer exercitosAPerder = 0;
	
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
	}
	
	public boolean getJogadorHumanoPodeAtacar() {
		if (!usuario.getJogo().getUsuarioDaVez().getJogadorHumano()) {
			return false;
		}
		
		for (Territorio vizinho : getVizinhos()) {
			if (vizinho.getUsuario().getJogadorHumano()) {
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

	public Integer getExercitosAPerder() {
		return exercitosAPerder;
	}

	public void setExercitosAPerder(Integer exercitosAPerder) {
		this.exercitosAPerder = exercitosAPerder;
	}
	
	public void aumentaExercitosAPerder(Integer quantidade) {
		this.exercitosAPerder += quantidade;
	}
	
}