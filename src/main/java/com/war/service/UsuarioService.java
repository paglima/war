package com.war.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.war.dados.Territorio;
import com.war.dados.Usuario;
import com.war.dao.UsuarioDao;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;
	
	public List<Usuario> pegaTodosUsuarios() {
		return usuarioDao.findAll();
	}

	public void ataque(Territorio territorioAtacante, Territorio territorioDefensor) {
		// TODO Auto-generated method stub
	}
	
}