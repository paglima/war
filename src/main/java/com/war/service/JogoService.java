package com.war.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.war.dados.Carta;
import com.war.dados.Continente;
import com.war.dados.Jogada;
import com.war.dados.Jogo;
import com.war.dados.Territorio;
import com.war.dados.Usuario;
import com.war.dao.ContinenteDao;
import com.war.dao.TerritorioDao;
import com.war.form.TerritorioForm;

@Service
public class JogoService {
	
	@Autowired
	private ObjetivoService objetivoService;
	
	@Autowired
	private CartaService cartaService;
	
	@Autowired
	private TerritorioDao territorioDao;
	
	@Autowired
	private ContinenteDao continenteDao;
	
	public void preparaJogadores(List<Usuario> usuarios) {
		selecionaJogadorInicio(usuarios);
		distribuiTerritorio(usuarios);
		distribuiExercitosInciais(usuarios);
		objetivoService.sorteiaObjetivos(usuarios);
	}
	
	private void selecionaJogadorInicio(List<Usuario> usuarios) {
		Integer randomico = (int) (Math.random() * (usuarios.size()));
		
		for (int i = 0; i < usuarios.size(); i++) {
			if (i == randomico) {
				usuarios.get(i).setTurnoDaJogada(Boolean.TRUE);
				usuarios.get(i).setIniciador(Boolean.TRUE);
				continue;
			}
			
			usuarios.get(i).setTurnoDaJogada(Boolean.FALSE);
		}
	}

	private void distribuiTerritorio(List<Usuario> usuarios) {
		List<Territorio> territorios = territorioDao.findAll();
		Collections.shuffle(territorios);
		
		Integer indice = 0;
		
		for (Territorio territorio : territorios) {
			if (indice >= usuarios.size()) {
				indice = 0;
			}
			
			Usuario usuario = usuarios.get(indice);
			if (usuario != null) {
				territorio.setQuantidadeExercito(1);
				usuario.addTerritorio(territorio);
				territorio.setUsuario(usuario);
			}
			
			indice++;
		}
	}

	public void distribuiExercitosInciais(List<Usuario> usuarios) {
		for (Usuario usuario : usuarios) {
			usuario.setExercitoSobrando((usuario.getTerritorios() != null) ? (usuario.getTerritorios().size() / 2) : 0);
		}
	}

	public void preparaTerritorios(List<Usuario> usuarios, TerritorioForm territorioForm, List<Carta> todasAsCartas) throws Exception {
		atualizaTerritoriosPerdidos(usuarios, territorioForm, todasAsCartas);
		
		for (Usuario usuario : usuarios) {
			Integer totalExercitosDistribuidos = 0;
			
			for (Territorio territorio : usuario.getTerritorios()) {
				territorio.atualizaInfo(territorioForm.getTerritorioById(territorio.getIdTerritorio()));
				totalExercitosDistribuidos += territorio.getQuantidadeExercito() - 1;
			}
			
			if (usuario.getExercitoSobrando() > 0 && usuario.getExercitoSobrando() != totalExercitosDistribuidos) {
				throw new Exception("Erro de distribuição de território");
			}
			
			usuario.setExercitoSobrando(0);
		}
	}

	private void atualizaTerritoriosPerdidos(List<Usuario> usuarios, TerritorioForm territorioForm, List<Carta> todasAsCartas) {
		for (Territorio territorioModificado : territorioForm.getTerritorios()) {
			Territorio territorio = getTerritorioById(territorioModificado.getIdTerritorio(), usuarios);
			
			if (territorio != null) {
				if (territorioModificado.getCorDoConquistador() != null && !"".equals(territorioModificado.getCorDoConquistador())) {
					territorio.atualizaJogadorDonoDoTerritorio(territorioModificado, territorioModificado.getCorDoConquistador());
				
					if (territorio.getUsuario().getJogadorHumano() && territorio.getUsuario().getConquistouTerritorio()) {
						territorio.getUsuario().addCarta(cartaService.sorteiaCarta(todasAsCartas));
						territorio.getUsuario().setConquistouTerritorio(Boolean.FALSE);
					}
				}
			}
		}
		
	}

	private Territorio getTerritorioById(Long idTerritorio, List<Usuario> usuarios) {
		for (Usuario usuario : usuarios) {
			for (Territorio territorio : usuario.getTerritorios()) {
				if (territorio.getIdTerritorio().equals(idTerritorio)) {
					return territorio;
				}
			}
		}
		
		return null;
	}

	public void distribuiExercitoInimigo(List<Usuario> usuarios) {
		for (Usuario usuario : usuarios) {
			if (!usuario.getJogadorHumano()) {
				usuario.getTerritorios().get(0).setQuantidadeExercito(1 + usuario.getExercitoSobrando());
			}
		}
	}

	public boolean jogoJaComecou(Usuario usuario) {
		if (usuario != null && !usuario.vazio() && usuario.getJogo() != null && usuario.getJogo().getPartidaComecada()) {
			return true;
		}
		
		return false;
	}

	public Jogada processaJogadaInimiga(Jogo jogo, List<Carta> cartas) throws Exception {
		Usuario atacante = jogo.getUsuarioDaVez();
		Jogada jogada = jogo.processaMovimento(atacante);
		
		if (atacante.getConquistouTerritorio() && !atacante.getJaPegouCartaNoTurno()) {
			atacante.addCarta(cartaService.sorteiaCarta(cartas));
			atacante.setJaPegouCartaNoTurno(Boolean.TRUE);
			atacante.setConquistouTerritorio(Boolean.FALSE);
		}
		
		return jogada;
	}

	public void passaTurno(Jogo jogo) {
		Integer indice = 0;
		
		for (int i = 0; i < jogo.getUsuarios().size(); i++) {
			if (jogo.getUsuarios().get(i).getTurnoDaJogada()) {
				indice = i;
				jogo.getUsuarios().get(i).setTurnoDaJogada(Boolean.FALSE);
				break;
			}
		}
		
		if ((indice + 1) < jogo.getUsuarios().size()) {
			indice++;
		} else {
			indice = 0;
		}
		
		while (!jogo.getUsuarios().get(indice).getAindaNoJogo()) {
			if (indice >= jogo.getUsuarios().size()) {
				indice = 0;
			}
		}
		
		jogo.getUsuarios().get(indice).setTurnoDaJogada(Boolean.TRUE);
		
		if (jogo.getUsuarios().get(indice).getCor().equals(jogo.getUsuarioIniciador().getCor())) {
			jogo.setTurno(jogo.getTurno() + 1);
		}
	}

	public void coletaExercitoParaUsuarioDaVez(Usuario usuarioDaVez) {
		if (usuarioDaVez != null && usuarioDaVez.getExercitoSobrando() == 0) {
			usuarioDaVez.setExercitoSobrando((usuarioDaVez.getTerritorios() != null) ? (usuarioDaVez.getTerritorios().size() / 2) : 0);
		}
	}

	public void distribuiExercitoParaInimigoDaVez(Usuario usuarioDaVez) {
		Integer quantidadeExercito = usuarioDaVez.getTerritorioComMaiorExercitoParaAtaque().getQuantidadeExercito();
		Integer exercitoSobrando = usuarioDaVez.getExercitoSobrando();
		
		usuarioDaVez.getTerritorioComMaiorExercitoParaAtaque().setQuantidadeExercito(quantidadeExercito + exercitoSobrando);
		usuarioDaVez.setExercitoSobrando(0);
	}
	
	public Usuario verificaFimDoJogo(Jogo jogo) {
		List<Continente> continentes = continenteDao.findAll();
		
		return jogo.verificaFim(continentes);
	}
	
}