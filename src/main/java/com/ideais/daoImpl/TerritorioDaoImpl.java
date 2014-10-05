package com.ideais.daoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ideais.dados.Territorio;
import com.ideais.dao.TerritorioDao;

@Repository
@Transactional
public class TerritorioDaoImpl extends GenericDaoImpl<Territorio> implements TerritorioDao{

	@Override
	public List<Territorio> findAll() {
		return super.findAll("from Territorio");
	}
	
	@Override
	public void remove(Territorio object) {
		super.remove(object);
	}
	
	@Override
	public Territorio findById(Long id) {
		return super.findById(id);
	}
	@Override
	public void saveOrUpdate(Territorio object) {
		super.saveOrUpdate(object);
	}

	@Override
	public Territorio findByName(String name) {
		return (Territorio) sessionFactory.getCurrentSession()
										.createQuery("from Territorio as t where t.nomeTerritorio = :nome ")
										.setParameter("nome", name)
										.list()
										.get(0);
	}
	

}
