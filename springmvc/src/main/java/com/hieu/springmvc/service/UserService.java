package com.hieu.springmvc.service;

import java.util.ArrayList;
import java.util.List;


import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hieu.springmvc.model.MyUser;
import com.hieu.springmvc.model.MyUserRole;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private SessionFactory sessionFactory;
	

	
	public boolean authenticateLogIn(String username, String password) {
		String hql = "select u.username from MyUser u where u.username = :username and u.password = :password";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql)
										.setParameter("username", username)
										.setParameter("password", password);
		List result = query.list();
		System.out.println(result);
		return result.size() == 1;
	}
	
	/*
	 * returns true if username is available
	 */
	public boolean authenticateSignUp(String username) {
		String hql = "select u.username from MyUser u where u.username = :username";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql).setParameter("username", username);
		List result = query.list();
		System.out.println(result.size());
		return result.size() < 1; 
	}
	
	public void createUser(String username, String password, String firstName, String lastName) {
		MyUser user = new MyUser();
		user.setUsername(username);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		System.out.println(sessionFactory);
		MyUserRole userRole = new MyUserRole();
		userRole.setRole("ROLE_USER");
		user.getUserRole().add(userRole);
		Session session = getSessionFactory().getCurrentSession();
		session.persist(user);
		
	}
	
	public MyUser findUserByUsername(String username) {
		MyUser user = new MyUser();
		String hql = "from MyUser u where u.username = :username";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql).setParameter("username", username);
		user = (MyUser) query.list().get(0);
		
		return user;
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
}