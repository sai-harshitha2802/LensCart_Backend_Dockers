package com.capg.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Min;

import jakarta.validation.constraints.Positive;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lenses")
public class Lenses {

	@Id
	@GeneratedValue(generator = "lens-id-generator")
	@GenericGenerator(name = "lens-id-generator", strategy = "com.capg.generator.LensIdGenerator")
	@Column(name = "lens_id")
	private String lensId;

	@Column(name = "brand")
	@NotNull(message = "Brand cannot be null")
	@Size(min = 1, max = 100, message = "Brand name must be between 1 and 100 characters")
	private String brand;

	@Column(name = "lens_image")
	@Size(min = 1, max = 255, message = "Image URL should be between 1 and 255 characters")
	private String lensImage;

	@Column(name = "shape")
	@NotNull(message = "Shape cannot be null")
	@Size(min = 1, max = 50, message = "Shape should be between 1 and 50 characters")
	private String shape;

	@Column(name = "color")
	@NotNull(message = "Colour cannot be null")
	@Size(min = 1, max = 50, message = "Colour should be between 1 and 50 characters")
	private String colour;

	@Column(name = "price")
	@Positive(message = "Price must be positive")
	@NotNull(message = "Price cannot be null")
	private double price;

	@Column(name = "quantity")
	@Min(value = 0, message = "Quantity cannot be negative")
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

}