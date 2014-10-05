package com.ideais.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class GenericDao<T extends Object>{

	@Autowired
	protected SessionFactory sessionFactory;
	
	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public List<T> findAll(String tableName) {
		return sessionFactory.getCurrentSession().createQuery(tableName).list();
	}

	@SuppressWarnings("unchecked")
	public T findById(Long id) {
		return (T) sessionFactory.getCurrentSession().get(getPersistentClass(), id);
	}

	@SuppressWarnings("unchecked")
	public void saveOrUpdate(T object) {
		T p = (T) sessionFactory.getCurrentSession().merge(object);
		sessionFactory.getCurrentSession().save(p);
	}


	public void remove(T object) {
		sessionFactory.getCurrentSession().delete(object);
	}
	
	@SuppressWarnings("unchecked")
	public Class<T> getPersistentClass() {
		if ( persistentClass == null ) {
			this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

		}
		return persistentClass;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
