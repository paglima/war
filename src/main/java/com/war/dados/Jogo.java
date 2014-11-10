package com.war.dados;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "JOGO")
public class Jogo {
	
	@Id
	@SequenceGenerator( name = "sequence", sequenceName = "JOGO_SEQ_ID" )
    @GeneratedValue( generator = "sequence", strategy = GenerationType.AUTO )
	@Column(name = "ID_JOGO", nullable = false, unique = true)
	private Long idJogo;
	
	@Column(name = "NM_NOME", nullable = false, unique = true)	
	private String nome;
	
	@Column(name = "NM_TIPO", nullable = false)
	private String tipo;
	
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Usuario.class)
	@JoinColumn(name = "ID_JOGO", nullable = true)
	private List<Usuario> usuarios;
	
	@Transient
	private Integer turno = 0;
	
	@Transient
	private Boolean partidaComecada = Boolean.FALSE;
	
	public Jogo(String tipo, Usuario usuario, Integer quantidadeInimigos, String nomeIdentificador) {
		this.tipo = tipo;
		partidaComecada = Boolean.TRUE;
		nome = nomeIdentificador;
		getUsuarios().add(usuario);
		usuario.setJogo(this);
		
		List<Cor> cores = Cor.removeCor(Arrays.asList(Cor.values()), usuario.getCor());
		Collections.shuffle(cores);
		
		for (int i = 1; i <= quantidadeInimigos; i++) {
			Usuario inimigo = new Usuario();
			inimigo.setJogo(this);
			inimigo.setNomeUsuario("Inimigo " + i);
			inimigo.setCor(cores.get(i - 1).getNomeCor());
			
			usuarios.add(inimigo);
		}
	}
	
	public List<Usuario> getUsuarios() {
		if (usuarios == null) {
			usuarios = new ArrayList<Usuario>();
		}
		
		return usuarios;
	}
	
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(Long idJogo) {
		this.idJogo = idJogo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getTurno() {
		return turno;
	}

	public void setTurno(Integer turno) {
		this.turno = turno;
	}

	public Boolean getPartidaComecada() {
		return partidaComecada;
	}

	public void setPartidaComecada(Boolean partidaComecada) {
		this.partidaComecada = partidaComecada;
	}

	public boolean turnoDoInimigo() {
		for (Usuario usuario : getUsuarios()) {
			if (!usuario.getJogadorHumano() && usuario.getTurnoDaJogada()) {
				return true;
			}
		}
		
		return false;
	}

	public Usuario getUsuarioDaVez() {
		for (Usuario usuario : getUsuarios()) {
			if (usuario.getTurnoDaJogada()) {
				return usuario;
			}
		}
		
		return null;
	}

	public Jogada processaMovimento(Usuario usuarioAtacante) {
		Territorio atacante = usuarioAtacante.getTerritorioComMaiorExercitoParaAtaque();
		Territorio defensor = usuarioAtacante.getTerritorioASerAtacado(atacante);
		
		if (atacante == null) {
			return null;
		}
		
		Jogada jogada = new Jogada();
		jogada.getAtaques().add(new Ataque(atacante, defensor));
		
		while (jogada.naoContemDefensorHumano()) {
			jogada.processaAtaque(jogada.getUltimoAtaque());
			
			atacante = usuarioAtacante.getTerritorioComMaiorExercitoParaAtaque();
			defensor = usuarioAtacante.getTerritorioASerAtacado(atacante);
			
			if (atacante == null || defensor == null) {
				return jogada;
			}
			
			jogada.getAtaques().add(new Ataque(atacante, defensor));
		}
		
		jogada.getSumarioJogada().add("Você está sendo atacado. (" + atacante.getNomeTerritorio() + " está atacando " + defensor.getNomeTerritorio()+ ".");
		
		return jogada;
	}

	public void zeraExercitosAPerder() {
		for (Usuario usuario : getUsuarios()) {
			for (Territorio territorio : usuario.getTerritorios()) {
				territorio.setExercitosAPerder(0);
			}
		}
	}
	
}