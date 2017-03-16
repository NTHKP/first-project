package com.hieu.springmvc.dao;

import java.util.List;

public interface GenericDao<T> {
	
	List<T> findAll();
	T create(T t);
	T find(Object id);
	T update(T t);
	void delete(T t);

}
