package com.war.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.war.dados.Jogada;
import com.war.dados.Jogo;
import com.war.dados.Territorio;
import com.war.dados.Usuario;
import com.war.dao.TerritorioDao;
import com.war.form.TerritorioForm;

@Service
public class JogoService {
	
	@Autowired
	private ObjetivoService objetivoService;
	
	@Autowired
	private TerritorioDao territorioDao;
	
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

	public void preparaTerritorios(List<Usuario> usuarios, TerritorioForm territorioForm) throws Exception {
		atualizaTerritoriosPerdidos(usuarios, territorioForm);
		
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

	private void atualizaTerritoriosPerdidos(List<Usuario> usuarios, TerritorioForm territorioForm) {
		for (Territorio territorioModificado : territorioForm.getTerritorios()) {
			Territorio territorio = getTerritorioById(territorioModificado.getIdTerritorio(), usuarios);
			
			if (territorio != null) {
				if (territorioModificado.getFoiConquistado()) {
					territorio.atualizaJogadorDonoDoTerritorio(territorioModificado);
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

	public Jogada processaJogadaInimiga(Jogo jogo) throws Exception {
		Usuario atacante = jogo.getUsuarioDaVez();
		
		return jogo.processaMovimento(atacante);
	}

	public void passaTurno(Jogo jogo) {
		jogo.setTurno(jogo.getTurno() + 1);
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
	}

	public void coletaExercitoParaUsuarioDaVez(Usuario usuarioDaVez) {
		if (usuarioDaVez != null) {
			usuarioDaVez.setExercitoSobrando((usuarioDaVez.getTerritorios() != null) ? (usuarioDaVez.getTerritorios().size() / 2) : 0);
		}
	}

	public void distribuiExercitoParaInimigoDaVez(Usuario usuarioDaVez) {
		Integer quantidadeExercito = usuarioDaVez.getTerritorioComMaiorExercitoParaAtaque().getQuantidadeExercito();
		Integer exercitoSobrando = usuarioDaVez.getExercitoSobrando();
		
		usuarioDaVez.getTerritorioComMaiorExercitoParaAtaque().setQuantidadeExercito(quantidadeExercito + exercitoSobrando);
		usuarioDaVez.setExercitoSobrando(0);
	}
	
}