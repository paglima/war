package com.ideais.dao;

import java.util.List;

import com.ideais.dados.Usuario;

public interface GenericDao<T extends Object> {
	
	List<T> findAll(String tableName);

	Usuario findById(Long id);

	void saveOrUpdate(T object);

	void remove(T object);

}
