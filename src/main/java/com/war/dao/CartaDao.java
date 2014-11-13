package com.war.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.war.dados.Carta;

@Repository
@Transactional
public class CartaDao extends GenericDao<Carta>{

	public List<Carta> findAll() {
		return super.findAll("from Carta");
	}
	
	@Override
	public void remove(Carta object) {
		super.remove(object);
	}
	
	@Override
	public Carta findById(Long id) {
		return super.findById(id);
	}
	
	@Override
	public void saveOrUpdate(Carta object) {
		super.saveOrUpdate(object);
	}
	
	public Carta findByName(String name) {
		return (Carta) sessionFactory.getCurrentSession()
										 .createCriteria(Carta.class)
										 .add(Restrictions.eq("nomeContinente", name))
										 .uniqueResult();
	}
	

}
