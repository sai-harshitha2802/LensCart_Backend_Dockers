package com.capg.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "frames")
public class Frames {

	@Id
	@GeneratedValue(generator = "frame-id-generator")
	@GenericGenerator(name = "frame-id-generator", strategy = "com.capg.generator.FrameIdGenerator")
	@Column(name = "frame_id")
	private String frameId;

	@Column(name = "frame_name")
	private String frameName;

	@Column(name = "brand")
	private String brand;

	@Column(name = "color")
	private String color;

	@Column(name = "price")
	private double price;

	

	@Column(name = "shapeOptions")
	private String shape;
	@Column(name="quantity")
	private int quantity;
	@Column(name="image")
	private String imageUrl;


	public Frames() {
		super();
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

	public Frames(String frameId, String frameName, String brand, String color, double price,
			String shape, int quantity, String imageUrl) {
		super();
		this.frameId = frameId;
		this.frameName = frameName;
		this.brand = brand;
		this.color = color;
		this.price = price;
		
		this.shape = shape;
		this.quantity = quantity;
		this.imageUrl = imageUrl;
	}

	

	

}
