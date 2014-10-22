package com.war.service;
import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.war.dados.Objetivo;
import com.war.dados.Usuario;
import com.war.dao.ObjetivoDao;

@Service
public class ObjetivoService{
		
	private static final int NUMERO_DE_OBJETIVOS = 14;
	
	@Autowired
	private ObjetivoDao objetivoDao;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public Objetivo sorteiaObjetivo() {
		Random random = new Random();
		Integer idObjetivoSorteado = random.nextInt(NUMERO_DE_OBJETIVOS) + 1;
		ArrayList<Usuario> usuarios = (ArrayList<Usuario>) usuarioService.pegaTodosUsuarios();
		
		while (confereRedundanciaDeObjetivos(usuarios, idObjetivoSorteado)) {
			idObjetivoSorteado = random.nextInt(NUMERO_DE_OBJETIVOS) + 1;
		}
		
		return objetivoDao.findById(idObjetivoSorteado.longValue());
	}	

	private boolean confereRedundanciaDeObjetivos(ArrayList<Usuario> usuarios, Integer idObjetivoSorteado) {
		for (Usuario usuario : usuarios) {
			if (usuario.getObjetivo().getIdObjetivo().equals(idObjetivoSorteado)) {
				return true;
			}
		}
		
		return false;
	}

}