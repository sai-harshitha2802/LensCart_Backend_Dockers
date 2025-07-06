package com.capg.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "glass")
public class Glass {
	@Id
    @GeneratedValue(generator = "glass-id-generator")
    @GenericGenerator(
        name = "glass-id-generator",
        strategy = "com.capg.generator.GlassIdGenerator"
    )
	@Column(name = "glass_id")
    private String glassId;

    @NotBlank(message = "Glass name cannot be blank")
    @Column(nullable = false)
    private String glassName;

    @NotBlank(message = "Brand cannot be blank")
    @Column(nullable = false)
    private String brand;

    @Min(value = 1, message = "Price must be greater than 0")
    @Column(nullable = false)
    private double price;

    @NotBlank(message = "Type cannot be blank")
    @Column(nullable = false)
    private String type;
    
    @Column(name="quantity")
    private int quantity;

    private double powerRange;

    @NotBlank(message = "Glass image URL cannot be blank")
    @Column(nullable = false)
    private String glassImage;

    // Default Constructor
    public Glass() {
    }

	public Glass(String glassId, @NotBlank(message = "Glass name cannot be blank") String glassName,
			@NotBlank(message = "Brand cannot be blank") String brand,
			@Min(value = 1, message = "Price must be greater than 0") double price,
			@NotBlank(message = "Type cannot be blank") String type, int quantity, double powerRange,
			@NotBlank(message = "Glass image URL cannot be blank") String glassImage) {
		super();
		this.glassId = glassId;
		this.glassName = glassName;
		this.brand = brand;
		this.price = price;
		this.type = type;
		this.quantity = quantity;
		this.powerRange = powerRange;
		this.glassImage = glassImage;
	}

	public String getGlassId() {
		return glassId;
	}

	public void setGlassId(String glassId) {
		this.glassId = glassId;
	}

	public String getGlassName() {
		return glassName;
	}

	public void setGlassName(String glassName) {
		this.glassName = glassName;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPowerRange() {
		return powerRange;
	}

	public void setPowerRange(double powerRange) {
		this.powerRange = powerRange;
	}

	public String getGlassImage() {
		return glassImage;
	}

	public void setGlassImage(String glassImage) {
		this.glassImage = glassImage;
	}

    // Parameterized Constructor
    

    
}
