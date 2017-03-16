package com.hieu.springmvc.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.hieu.springmvc.model.MyUser;
import com.hieu.springmvc.model.Order;
import com.hieu.springmvc.model.Product;
import com.hieu.springmvc.model.ProductType;

@Service
@Transactional
public class OrderService {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserService userService;
	
	
	public Order findOrderById(int orderId) {
		String hql = "FROM Order o WHERE o.orderId = :orderId";
		return (Order) sessionFactory.getCurrentSession().createQuery(hql).setParameter("orderId", orderId)
									.list().get(0);
	}
	
	public void createOrder(List<Product> products, double totalPrice) {
		UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MyUser myUser = userService.findUserByUsername(user.getUsername());
//		for(Product product : products) {
//			order.getProducts().add(product);
//		}
		Order order = new Order();
		order.setProducts(products);
		order.setOrderDate(new Date());
		order.setTotalPrice(totalPrice);
		myUser.getOrders().add(order);
		sessionFactory.getCurrentSession().update(myUser);
	}
	
	public List<Order> getAllOrders() {
		UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MyUser myUser = userService.findUserByUsername(user.getUsername());
		return (List<Order>) myUser.getOrders();
	}
	
	
	
}
