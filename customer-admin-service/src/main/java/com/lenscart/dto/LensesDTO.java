package com.lenscart.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class LensesDTO {

	private String lensId;

	@NotEmpty(message = "Brand cannot be empty")
	private String brand;

	@NotEmpty(message = "Lens image cannot be empty")
	private String lensImage;

	@NotEmpty(message = "Shape cannot be empty")
	private String shape;

	@NotEmpty(message = "Colour cannot be empty")
	private String colour;

	@Positive(message = "Price must be greater than zero")
	private double price;

	@Positive(message = "Quantity must be positive")
	private int quantity;
	private String name;
	public String getLensId() {
		return lensId;
	}
	public void setLensId(String lensId) {
		this.lensId = lensId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getLensImage() {
		return lensImage;
	}
	public void setLensImage(String lensImage) {
		this.lensImage = lensImage;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LensesDTO(String lensId, @NotEmpty(message = "Brand cannot be empty") String brand,
			@NotEmpty(message = "Lens image cannot be empty") String lensImage,
			@NotEmpty(message = "Shape cannot be empty") String shape,
			@NotEmpty(message = "Colour cannot be empty") String colour,
			@Positive(message = "Price must be greater than zero") double price,
			@Positive(message = "Quantity must be positive") int quantity, String name) {
		super();
		this.lensId = lensId;
		this.brand = brand;
		this.lensImage = lensImage;
		this.shape = shape;
		this.colour = colour;
		this.price = price;
		this.quantity = quantity;
		this.name = name;
	}
	

	
}