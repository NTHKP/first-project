package com.hieu.springmvc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="product_types")
public class ProductType {
	
	@Id @GeneratedValue
	private int productTypeId;
	private String productTypeName;
	private double productTypePrice;
	private int quantityInStock;
	
	public ProductType() {}
	
	public ProductType(String productTypeName, double productTypePrice, int quantityInStock) {
		super();
		this.productTypeName = productTypeName;
		this.productTypePrice = productTypePrice;
		this.quantityInStock = quantityInStock;
	}
	
	public int getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(int productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public double getProductTypePrice() {
		return productTypePrice;
	}
	public void setProductTypePrice(double productTypePrice) {
		this.productTypePrice = productTypePrice;
	}
	public int getQuantityInStock() {
		return quantityInStock;
	}
	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

}
