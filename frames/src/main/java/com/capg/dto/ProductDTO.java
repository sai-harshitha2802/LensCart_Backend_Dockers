package com.project1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
	private String id;
	private String brand;
	private double price;
	private String description;
	private int quantity;
	private String imageUrl;

	public ProductDTO() {
	}

	public ProductDTO(String id, String brand, double price, String description, int quantity,String imageUrl) {
		super();
		this.id = id;
		this.brand = brand;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
		this.imageUrl=imageUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

	

}
