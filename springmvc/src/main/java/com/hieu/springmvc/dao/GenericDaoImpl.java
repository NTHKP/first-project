package com.hieu.springmvc.dao;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class GenericDaoImpl<T> implements GenericDao<T> {
	
	@Autowired
	SessionFactory sessionFactory;
	
	private Class<T> t;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		String hql = "SELECT t FROM " + getT().getSimpleName() + " t";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	@Override
	public T create(T t) {
		sessionFactory.getCurrentSession().persist(t);
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T find(Object id) {
		T t = (T) sessionFactory.getCurrentSession().get(getT(), (Serializable) id);
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T update(T t) {
		T updatedT = (T) sessionFactory.getCurrentSession().merge(t);
		return updatedT;
	}

	@Override
	public void delete(T t) {
		sessionFactory.getCurrentSession().delete(t);
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Class<T> getT() {
		return t;
	}

}
