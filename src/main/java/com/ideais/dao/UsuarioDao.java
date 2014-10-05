package com.ideais.dao;

import java.util.List;

import com.ideais.dados.Usuario;

public interface UsuarioDao {
	
	List<Usuario> findAll();

	Usuario findById(Long id);

	void saveOrUpdate(Usuario usuario);

	void remove(Usuario usuario);

}
