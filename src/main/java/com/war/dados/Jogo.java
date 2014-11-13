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
	
	@Transient
	private Boolean distrubuicaoInicial = Boolean.TRUE;
	
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

	public Jogada processaMovimento(Usuario usuarioAtacante) throws Exception {
		Territorio atacante = usuarioAtacante.getTerritorioComMaiorExercitoParaAtaque();
		Territorio defensor = usuarioAtacante.getTerritorioASerAtacado(atacante);
		
		if (atacante == null || defensor == null || 
		   (atacante.getQuantidadeExercito() < defensor.getQuantidadeExercito())) {
			return null;
		}
		
		Jogada jogada = new Jogada();
		jogada.getAtaques().add(new Ataque(atacante, defensor));
		
		while (jogada.naoContemDefensorHumano()) {
			jogada.processaAtaque(jogada.getUltimoAtaque());
			
			atacante = usuarioAtacante.getTerritorioComMaiorExercitoParaAtaque();
			defensor = usuarioAtacante.getTerritorioASerAtacado(atacante);
			
			if (atacante == null || defensor == null || 
			   (atacante.getQuantidadeExercito() < defensor.getQuantidadeExercito())) {
				return jogada;
			}
			
			jogada.getAtaques().add(new Ataque(atacante, defensor));
		}
		
		jogada.getSumarioJogada().add("Você está sendo atacado. (" + atacante.getNomeTerritorio() + " está atacando " + defensor.getNomeTerritorio()+ ").");
		
		return jogada;
	}

	public Usuario getUsuarioHumano() {
		for (Usuario usuario : getUsuarios()) {
			if (usuario.getJogadorHumano()) {
				return usuario;
			}
		}
	
		return null;
	}

	public Usuario getUsuarioPelaCor(String cor) {
		for (Usuario usuario : getUsuarios()) {
			if (usuario.getCor().equals(cor)) {
				return usuario;
			}
		}
	
		return null;
	}

	public Usuario getUsuarioIniciador() {
		for (Usuario usuario : getUsuarios()) {
			if (usuario.getIniciador()) {
				return usuario;
			}
		}
	
		return null;
	}

	public Boolean getDistrubuicaoInicial() {
		return distrubuicaoInicial;
	}

	public void setDistrubuicaoInicial(Boolean distrubuicaoInicial) {
		this.distrubuicaoInicial = distrubuicaoInicial;
	}

	public Usuario verificaFim(List<Continente> continentes) {
		for (Usuario usuario : getUsuarios()) {
			if (verificaSeGanhou(usuario, continentes)) {
				return usuario;
			}
		}
		
		return null;
	}

	private boolean verificaSeGanhou(Usuario usuario, List<Continente> continentes) {
		if (usuario.getObjetivo().getIdObjetivo() == 1) return verificaObjetivoTresContinentes(usuario, continentes, "Europa", "Oceania");
		if (usuario.getObjetivo().getIdObjetivo() == 2) return verificaObjetivoTresContinentes(usuario, continentes, "America do Sul", "Asia");
		if (usuario.getObjetivo().getIdObjetivo() == 3) return verificaObjetivoTresContinentes(usuario, continentes, "Europa", "America do Sul");
		if (usuario.getObjetivo().getIdObjetivo() == 4) return verificaObjetivoDezoitoTerritoriosDois(usuario);
		if (usuario.getObjetivo().getIdObjetivo() == 5) return verificaObjetivoDoisContinentes(usuario, continentes, "Asia", "Africa");
		if (usuario.getObjetivo().getIdObjetivo() == 6) return verificaObjetivoDoisContinentes(usuario, continentes, "America do Norte", "Africa");
		if (usuario.getObjetivo().getIdObjetivo() == 7) return verificaSeConquistouNTerritorios(usuario, 24);
		if (usuario.getObjetivo().getIdObjetivo() == 8) return verificaObjetivoDoisContinentes(usuario, continentes, "America do Norte", "Oceania");
		if (usuario.getObjetivo().getIdObjetivo() == 9) return verificaObjetivoDaCor(usuario, "Azul");
		if (usuario.getObjetivo().getIdObjetivo() == 10) return verificaObjetivoDaCor(usuario, "Amarelo");
		if (usuario.getObjetivo().getIdObjetivo() == 11) return verificaObjetivoDaCor(usuario, "Vermelho");
		if (usuario.getObjetivo().getIdObjetivo() == 12) return verificaObjetivoDaCor(usuario, "Roxo");
		if (usuario.getObjetivo().getIdObjetivo() == 13) return verificaObjetivoDaCor(usuario, "Rosa");
		if (usuario.getObjetivo().getIdObjetivo() == 14) return verificaObjetivoDaCor(usuario, "Verde");
		
		return false;
	}

	private boolean verificaObjetivoDezoitoTerritoriosDois(Usuario usuario) {
		if (usuario.getTerritorios().size() < 18) {
			return false;
		}
		
		for (Territorio territorio : usuario.getTerritorios()) {
			if (territorio.getQuantidadeExercito() <= 1) {
				return false;
			}
		}
		
		return true;
	}

	private boolean verificaObjetivoDoisContinentes(Usuario usuario, List<Continente> continentes, String continentePrimeiro, String continenteSegundo) {
		Continente continentenUm = getContinenteByNome(continentes, continentePrimeiro);
		Continente continenteDois = getContinenteByNome(continentes, continenteSegundo);
		
		if (verificaSeContinenteFoiConquistado(usuario, continentenUm) && verificaSeContinenteFoiConquistado(usuario, continenteDois)) {
			return true;
		}
		
		return false;
	}

	private boolean verificaObjetivoTresContinentes(Usuario usuario, List<Continente> continentes, String continentePrimeiro, String continenteSegundo) {
		Continente continentenUm = getContinenteByNome(continentes, continentePrimeiro);
		Continente continenteDois = getContinenteByNome(continentes, continenteSegundo);
		
		if (verificaSeContinenteFoiConquistado(usuario, continentenUm) && verificaSeContinenteFoiConquistado(usuario, continenteDois) &&
		    verificaTerceiroContinente(usuario, continentenUm, continenteDois, continentes)) {
			return true;
		}
		
		return false;
	}

	private boolean verificaObjetivoDaCor(Usuario usuario, String cor) {
		boolean existeUsuarioDaCor = verificaSeTemUsuarioDaCorNoJogo(cor);
		
		if (existeUsuarioDaCor) {
			return verificaSeUsuarioEliminado(cor);
		}
		
		return verificaSeConquistouNTerritorios(usuario, 24);
	}
	
	private boolean verificaSeConquistouNTerritorios(Usuario usuario, int n) {
		if (usuario.getTerritorios().size() < n) {
			return false;
		}
		
		return true;
	}

	private boolean verificaSeUsuarioEliminado(String cor) {
		for (Usuario usuario : getUsuarios()) {
			if (cor.equals(usuario.getCor()) && !usuario.getAindaNoJogo()) {
				return true;
			}
		}
		return false;
	}

	private boolean verificaSeTemUsuarioDaCorNoJogo(String cor) {
		for (Usuario usuario : getUsuarios()) {
			if (cor.equals(usuario.getCor())) {
				return true;
			}
		}
		
		return false;
	}

	private boolean verificaTerceiroContinente(Usuario usuario, Continente continentePrimeiro, Continente continentenSegundo, List<Continente> continentes) {
		if (continentePrimeiro != null && continentenSegundo != null) {
			for (Continente continente : continentes) {
				if (continente.getNomeContinente().equals(continentePrimeiro.getNomeContinente()) && continente.getNomeContinente().equals(continentenSegundo.getNomeContinente())) {
					if (verificaSeContinenteFoiConquistado(usuario, continente) == true) {
						return true;
					}
				}
			}
		}
		
		return false;
	}

	private boolean verificaSeContinenteFoiConquistado(Usuario usuario, Continente continente) {
		if (continente != null) {
			for (Territorio territorio : continente.getTerritorios()) {
				if (!usuario.contemTerritorio(territorio)) {
					return false;
				}
			}
			return true;
		}
		
		return false;
	}

	private Continente getContinenteByNome(List<Continente> continentes, String nomeContinente) {
		for (Continente continente : continentes) {
			if (nomeContinente.equals(continente.getNomeContinente())) {
				return continente;
			}
		}
		
		return null;
	}
	
}