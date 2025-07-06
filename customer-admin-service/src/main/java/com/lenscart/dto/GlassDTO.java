package com.lenscart.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
public class GlassDTO {
    

    private String glassId;

    @NotBlank(message = "Brand name cannot be empty")
    private String brand;

    @NotBlank(message = "Glass image URL cannot be empty")
    private String glassImage;

    @NotBlank(message = "Glass name cannot be empty")
    private String glassName;

    @DecimalMin(value = "-20.0", message = "Power range must be at least -20.0")
    @DecimalMax(value = "20.0", message = "Power range must be at most 20.0")
    private double powerRange;

    @NotBlank(message = "Type cannot be empty")
    private String type;

    @Positive(message = "Price must be a positive number")
    private double price;
    @NotNull(message = "Quantity is mandatory")
	@Min(value = 1, message = "Quantity cannot be less than 0")
	private int quantity;

    public String getGlassId() {
		return glassId;
	}

	public void setGlassId(String glassId) {
		this.glassId = glassId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getGlassImage() {
		return glassImage;
	}

	public void setGlassImage(String glassImage) {
		this.glassImage = glassImage;
	}

	public String getGlassName() {
		return glassName;
	}

	public void setGlassName(String glassName) {
		this.glassName = glassName;
	}

	public double getPowerRange() {
		return powerRange;
	}

	public void setPowerRange(double powerRange) {
		this.powerRange = powerRange;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	// Default Constructor
    public GlassDTO() {
    }

    // Parameterized Constructor
    public GlassDTO(String glassId, String brand, String glassImage, String glassName, double powerRange, String type, double price) {
        this.glassId = glassId;
        this.brand = brand;
        this.glassImage = glassImage;
        this.glassName = glassName;
        this.powerRange = powerRange;
        this.type = type;
        this.price = price;
    }

   
}
