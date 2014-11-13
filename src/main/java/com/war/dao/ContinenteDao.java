package com.war.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.war.dados.Continente;

@Repository
@Transactional
public class ContinenteDao extends GenericDao<Continente>{

	public List<Continente> findAll() {
		return super.findAll("from Continente");
	}
	
	@Override
	public void remove(Continente object) {
		super.remove(object);
	}
	
	@Override
	public Continente findById(Long id) {
		return super.findById(id);
	}
	
	@Override
	public void saveOrUpdate(Continente object) {
		super.saveOrUpdate(object);
	}
	
	public Continente findByName(String name) {
		return (Continente) sessionFactory.getCurrentSession()
										 .createCriteria(Continente.class)
										 .add(Restrictions.eq("nomeContinente", name))
										 .uniqueResult();
	}
	

}
