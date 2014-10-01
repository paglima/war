package com.ideais.daoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ideais.dados.Objetivo;
import com.ideais.dao.ObjetivoDao;


@Repository
@Transactional
public class ObjetivoDaoImpl extends GenericDaoImpl<Objetivo> implements ObjetivoDao{
	
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
