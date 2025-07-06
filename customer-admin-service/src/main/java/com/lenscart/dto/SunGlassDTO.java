package com.lenscart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SunGlassDTO {

	private String sunGlassId;

	@NotBlank(message = "Sunglass name cannot be blank")
	@Size(min = 3, max = 50, message = "Sunglass name must be between 3 and 50 characters")
	private String sunGlassName;

	@NotBlank(message = "Brand cannot be blank")
	@Size(min = 2, max = 30, message = "Brand name must be between 2 and 30 characters")
	private String brand;

	@NotNull(message = "Price cannot be null")
	@Positive(message = "Price must be greater than zero")
	private double price;

	@NotBlank(message = "Frame color cannot be blank")
	private String frameColor;

	@NotBlank(message = "Frame shape cannot be blank")
	private String frameShape;

	@NotBlank(message = "Glass color cannot be blank")
	private String glassColor;
	@NotNull(message = "Quantity is mandatory")
	@Min(value = 1, message = "Quantity cannot be less than 0")
	private int quantity;
	private String image;
	public String getSunGlassId() {
		return sunGlassId;
	}
	public void setSunGlassId(String sunGlassId) {
		this.sunGlassId = sunGlassId;
	}
	public String getSunGlassName() {
		return sunGlassName;
	}
	public void setSunGlassName(String sunGlassName) {
		this.sunGlassName = sunGlassName;
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
	public String getFrameColor() {
		return frameColor;
	}
	public void setFrameColor(String frameColor) {
		this.frameColor = frameColor;
	}
	public String getFrameShape() {
		return frameShape;
	}
	public void setFrameShape(String frameShape) {
		this.frameShape = frameShape;
	}
	public String getGlassColor() {
		return glassColor;
	}
	public void setGlassColor(String glassColor) {
		this.glassColor = glassColor;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
