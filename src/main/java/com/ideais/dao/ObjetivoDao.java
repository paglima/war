package com.ideais.dao;

import java.util.List;

import com.ideais.dados.Objetivo;

public interface ObjetivoDao {

	List<Objetivo> findAll();

	Objetivo findById(Long id);

	void saveOrUpdate(Objetivo objetivo);

	void remove(Objetivo objetivo);

}
