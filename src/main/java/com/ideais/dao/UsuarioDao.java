package com.ideais.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ideais.dados.Usuario;

@Repository
@Transactional
public class UsuarioDao extends GenericDao<Usuario>{

	public List<Usuario> findAll() {
		return super.findAll("from Usuario");
	}

	@Override
	public void saveOrUpdate(Usuario usuario) {
		super.saveOrUpdate(usuario);
	}

	@Override
	public void remove(Usuario usuario) {
		super.remove(usuario);
	}
	
	@Override
	public Usuario findById(Long id) {
		return super.findById(id);
	}

	public void saveAll(List<Usuario> usuarios) {
		for (Usuario usuario : usuarios) {
			saveOrUpdate(usuario);
		}
	}
}
