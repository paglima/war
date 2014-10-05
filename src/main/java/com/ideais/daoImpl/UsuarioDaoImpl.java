package com.ideais.daoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ideais.dados.Usuario;
import com.ideais.dao.UsuarioDao;

@Repository
@Transactional
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario> implements UsuarioDao{

	@Override
	public List<Usuario> findAll() {
		return super.findAll("from Usuario");
	}

	@Override
	public void saveOrUpdate(Usuario usuario) {
		super.saveOrUpdate(usuario);
	}

	@Override
	public void remove(Usuario usuario) {
		super.saveOrUpdate(usuario);
	}
	
	@Override
	public Usuario findById(Long id) {
		return super.findById(id);
	}
}
