package com.ideais.dao;

import java.util.List;

import com.ideais.dados.Territorio;

public interface TerritorioDao {
	
	List<Territorio> findAll();

	Territorio findById(Long id);
	
	Territorio findByName(String name);

	void saveOrUpdate(Territorio territorio);

	void remove(Territorio territorio);

}
