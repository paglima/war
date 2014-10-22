package com.war.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.war.dados.Objetivo;
import com.war.dao.ObjetivoDao;


@Repository
@Transactional
public class ObjetivoDao extends GenericDao<Objetivo>{
	
	public List<Objetivo> findAll() {
		return super.findAll("from Objetivo");
	}

	@Override
	public Objetivo findById(Long id) {
		return super.findById(id);
	}

	@Override
	public void saveOrUpdate(Objetivo objetivo) {
		super.saveOrUpdate(objetivo);
	}

	@Override
	public void remove(Objetivo objetivo) {
		super.remove(objetivo);
	}

}
