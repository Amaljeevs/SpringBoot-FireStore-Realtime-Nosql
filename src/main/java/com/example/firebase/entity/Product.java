package com.example.firebase.entity;

import io.swagger.annotations.ApiModel;

@ApiModel("ProductDTO")
public class Product {

	private String name; //Primary key

	private int discount;

	private int price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", discount=" + discount + ", price=" + price + "]";
	}

}
