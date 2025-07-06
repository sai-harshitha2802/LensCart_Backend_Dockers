package com.lenscart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//@Getter
//@Setter
@AllArgsConstructor
//@NoArgsConstructor

public class FramesDTO {

    
    private String frameId;

    @NotBlank(message = "Frame name is mandatory")
    @Size(min = 1, max = 100, message = "Frame name must be between 1 and 100 characters")
    
    private String frameName;

    @NotBlank(message = "Brand is mandatory")
    @Size(min = 1, max = 50, message = "Brand name must be between 1 and 50 characters")
    
    private String brand;

    @NotBlank(message = "Color is mandatory")
    @Size(min = 1, max = 50, message = "Color name must be between 1 and 50 characters")
    
    private String color;

    @NotNull(message = "Price is mandatory")
    @Min(value = 0, message = "Price cannot be less than 0")
   
    private double price;

    @NotBlank(message = "Image URL is mandatory")
	private String imageUrl;

    @NotBlank(message = "Shape is mandatory")
    @Size(min = 1, max = 100, message = "Shape must be between 1 and 100 characters")
    
    private String shape;
    @NotNull(message = "Quantity is mandatory")
	@Min(value = 1, message = "Quantity cannot be less than 0")
	private int quantity;
    

	public FramesDTO(String frameId) {
		super();
		this.frameId = frameId;
	}

	public String getFrameId() {
		return frameId;
	}

	public void setFrameId(String frameId) {
		this.frameId = frameId;
	}

	public String getFrameName() {
		return frameName;
	}

	public void setFrameName(String frameName) {
		this.frameName = frameName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}
	
	

	public FramesDTO() {
		super();
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	

    
}


