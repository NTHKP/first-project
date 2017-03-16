package com.hieu.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hieu.springmvc.model.ProductType;
import com.hieu.springmvc.service.ProductTypeService;

@Controller
public class ProductTypeServiceController {
	
	@Autowired
	private ProductTypeService productTypeService;
	
	@RequestMapping(value="/add-products-to-stock", method=RequestMethod.GET)
	public String showAddProductsToStockPage() {
		return "add-products-to-stock";
	}
	
	@RequestMapping(value="/auth-add-products")
	public ModelAndView addProductsToStock(@RequestParam String productTypeName, @RequestParam  double productTypePrice,
										@RequestParam int quantityInStock) {
		
		productTypeService.addProductType(productTypeName, productTypePrice, quantityInStock);
		ModelAndView model = new ModelAndView().addObject("addProductsSuccessful", "Product added successfully!");
		model.setViewName("add-products-to-stock");
		return model;
	}
	
	@RequestMapping(value="/products")
	public String showProductsPage(@RequestParam(required=false) Integer pageNum, 
									@RequestParam(required=false) Integer numProductsPerPage,
									ModelMap model, HttpServletRequest request) {
		
		
		if (numProductsPerPage == null) {
			if (request.getSession().getAttribute("numProductsPerPage") != null) {
				numProductsPerPage = (Integer) request.getSession().getAttribute("numProductsPerPage");
			} else {
				numProductsPerPage = 4;
				
			}
			
		}
				
		if (pageNum == null) {
			pageNum = 1;
		}
		
		request.getSession().setAttribute("numProductsPerPage", numProductsPerPage);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("allProductTypes", productTypeService.getAllProductTypes());
		model.addAttribute("productTypes", productTypeService.getAllProductTypesPagination(pageNum, numProductsPerPage));
		return "/products";
	}
	
	
}
