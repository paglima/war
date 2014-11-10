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

	private void distribuiExercitosInciais(List<Usuario> usuarios) {
		for (Usuario usuario : usuarios) {
			usuario.setExercitoSobrando((usuario.getTerritorios() != null) ? (usuario.getTerritorios().size() / 2) : 0);
		}
	}

	public void preparaTerritorios(List<Usuario> usuarios, TerritorioForm territorioForm) throws Exception {
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

	public Jogada processaJogadaInimiga(Jogo jogo) {
		jogo.zeraExercitosAPerder();
		Usuario atacante = jogo.getUsuarioDaVez();
		
		return jogo.processaMovimento(atacante);
	}
	
}