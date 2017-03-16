package com.hieu.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hieu.springmvc.model.Product;
import com.hieu.springmvc.model.ProductType;
import com.hieu.springmvc.service.OrderService;
import com.hieu.springmvc.service.ProductTypeService;

@Controller
public class OrderServiceController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductTypeService productTypeService;
	
	@RequestMapping(value="/add-to-cart", method=RequestMethod.POST)
	public String addToShoppingCart(@RequestParam String productName, @RequestParam int quantity,
									HttpServletRequest request) {
		List<Product> products = null;
		
		if (request.getSession().getAttribute("productsChosen")==null) {
			products = new ArrayList<>();
		} else {
			products = (ArrayList<Product>) request.getSession().getAttribute("productsChosen");
		}
		ProductType productType = productTypeService.findProductTypeByName(productName);
		Product product = new Product();
		product.setProductName(productType.getProductTypeName());
		product.setProductPrice(productType.getProductTypePrice());
		product.setProductQuantity(quantity);
		product.setProductTotalPrice(productType.getProductTypePrice() * 10 * quantity /10);
		products.add(product);
		double totalAmount = 0;
		for (Product p : products) {
			totalAmount = (totalAmount*10 + p.getProductTotalPrice()*10)/10;
		}
		
		request.getSession().setAttribute("productsChosen", products);
		request.getSession().setAttribute("totalAmount", totalAmount);
		return "shopping-cart";
	}
	
	@RequestMapping(value="/shopping-cart", method=RequestMethod.GET)
	public String showShoppingCart() {
		return "shopping-cart";		
	}
	
	@RequestMapping(value="/remove-from-cart", method=RequestMethod.POST)
	public String removeFromCart(@RequestParam String productName, @RequestParam int quantity,
								HttpServletRequest request) {
		
		List<Product> products = (ArrayList<Product>) request.getSession().getAttribute("productsChosen");
		List<Product> productsToRemove = new ArrayList<>();
		double totalAmount = (double) request.getSession().getAttribute("totalAmount");
		for (Product p : products) {
			if (p.getProductName().equals(productName) && p.getProductQuantity() == quantity) {
				productsToRemove.add(p);
				totalAmount = (totalAmount*10 - p.getProductTotalPrice()*10)/10;
			}
		}
		products.removeAll(productsToRemove);
		request.getSession().setAttribute("productsChosen", products);
		request.getSession().setAttribute("totalAmount", totalAmount);
		return "redirect:shopping-cart";
	}
	
	@RequestMapping(value="/add-to-orders")
	public String addToOrders(HttpServletRequest request) {
		List<Product> products = (ArrayList<Product>) request.getSession().getAttribute("productsChosen");
		double totalAmount = (double) request.getSession().getAttribute("totalAmount");
		orderService.createOrder(products, totalAmount);
		products = new ArrayList<>();
		totalAmount = 0;
		request.getSession().setAttribute("productsChosen", products);
		request.getSession().setAttribute("totalAmount", totalAmount);
		return "redirect:shopping-cart";
	}
	
	@RequestMapping(value="/orders")
	public String showMyOrdersPage(ModelMap model) {
		model.addAttribute("listOfOrders", orderService.getAllOrders());
		return "orders";
	}
	
	
}
