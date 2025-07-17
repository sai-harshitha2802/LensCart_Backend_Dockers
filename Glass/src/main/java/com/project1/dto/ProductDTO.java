package com.project1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
	// @JsonProperty("lensId")
	private String id;
   
    private double price;
    private String name;
    private String description;
    private int quantity;
    private String imageUrl;


    public ProductDTO() {}


	public ProductDTO(String id, double price, String description,String name,int quantity,String imageUrl) {
		super();
		this.id = id;
		this.price = price;
		this.name = name;
		this.description = description;
		this.quantity=quantity;
		this.imageUrl=imageUrl;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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