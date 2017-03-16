package com.hieu.springmvc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hieu.springmvc.model.ProductType;

@Service
@Transactional
public class ProductTypeService {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public List<ProductType> getAllProductTypes() {
		String hql = "FROM ProductType";
		Session session = getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		List<ProductType> products = query.list();
		return products;
	}
	
	public List<ProductType> getAllProductTypesPagination(int pageNum, int numProductsPerPage) {
		String hql = "FROM ProductType";
		Session session = getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql)
							.setFirstResult((pageNum - 1) * numProductsPerPage)
							.setMaxResults(numProductsPerPage);
							
		List<ProductType> products = query.list();
		return products;
	}
	
	public ProductType findProductTypeByName(String productName) {
		String hql = "FROM ProductType pt WHERE pt.productTypeName = :productTypeName";
		return (ProductType) sessionFactory.getCurrentSession().createQuery(hql).setParameter("productTypeName", productName)
									.list().get(0);
	}
	
	public void addProductType(String productTypeName, double productTypePrice, int quantityInStock) {
		ProductType productType = new ProductType(productTypeName, productTypePrice, quantityInStock);
		getSessionFactory().getCurrentSession().persist(productType);
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
}
