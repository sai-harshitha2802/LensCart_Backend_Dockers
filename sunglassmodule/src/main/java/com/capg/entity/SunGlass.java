package com.capg.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sunGlasses")
public class SunGlass {

	@Id
	@GeneratedValue(generator = "sunglass-id-generator")
	@GenericGenerator(name = "sunglass-id-generator", strategy = "com.capg.generator.SunglassIdGenerator")

	@Column(name = "sun_glass_id")
	private String sunGlassId;

	@Column(name = "sunGlassName")
	private String sunGlassName;

	@Column(name = "brand")
	private String brand;

	@Column(name = "price")
	private double price;

	@Column(name = "frameColor")
	private String frameColor;

	@Column(name = "frameShape")
	private String frameShape;

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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Column(name = "glassColor")
	private String glassColor;
	private String image;
	private int quantity;

}
