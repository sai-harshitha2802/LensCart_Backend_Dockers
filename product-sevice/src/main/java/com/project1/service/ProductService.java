package com.project1.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project1.dto.ProductDTO;
import com.project1.exception.ProductNotFoundException;
import com.project1.feign.FramesClient;
import com.project1.feign.GlassesClient;
import com.project1.feign.LensClient;
import com.project1.feign.SunGlassClient;

@Service
public class ProductService implements IProductService {

	@Autowired
	private LensClient lensClient;

	@Autowired
	private FramesClient framesClient;

	@Autowired
	private GlassesClient glassesClient;

	@Autowired
	private SunGlassClient sunGlassClient;

	public List<ProductDTO> getAllProducts() {
		List<ProductDTO> allProducts = new ArrayList<>();

		try {
			allProducts.addAll(lensClient.getLenses());

		} catch (Exception e) {
			System.err.println("Error fetching lenses: " + e.getMessage());
		}

		try {
			allProducts.addAll(framesClient.getFrames());
			System.out.println(framesClient.getFrames());
		} catch (Exception e) {
			System.err.println("Error fetching frames: " + e.getMessage());
		}

		try {
			allProducts.addAll(glassesClient.getGlasses());
		} catch (Exception e) {
			System.err.println("Error fetching glasses: " + e.getMessage());
		}
		try {
			allProducts.addAll(sunGlassClient.getSunGlass());

		} catch (Exception e) {
			System.err.println("Error fetching sunglasses: " + e.getMessage());
		}

		return allProducts;
	}

	@Override
	public ProductDTO getProductById(String id) {
	    // Fetch all products from different microservices
	    List<ProductDTO> allProducts = getAllProducts();

	    return allProducts.stream()
	            .filter(product -> product.getId().equalsIgnoreCase(id))
	            .findFirst()
	            .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found")); // ✅ Throws exception if not found
	}
	public void reduceStock(String productId, int quantity) {
	    if (productId.startsWith("FRAME")) {
	        framesClient.reduceStock(productId, quantity);
	    } else if (productId.startsWith("LENS")) {
	        lensClient.reduceStock(productId, quantity);
	    } else if (productId.startsWith("GLASS")) {
	        glassesClient.reduceStock(productId, quantity);
	    } else if (productId.startsWith("SUN")) {
	        sunGlassClient.reduceStock(productId, quantity);
	    } else {
	        throw new RuntimeException("Unknown product type for ID: " + productId);
	    }
	}
	
	public void increaseStock(String productId, int quantity) {
	    if (productId.startsWith("FRAME")) {
	        framesClient.increaseStock(productId, quantity);
	    } else if (productId.startsWith("LENS")) {
	        lensClient.increaseStock(productId, quantity);
	    } else if (productId.startsWith("GLASS")) {
	        glassesClient.increaseStock(productId, quantity);
	    } else if (productId.startsWith("SUN")) {
	        sunGlassClient.increaseStock(productId, quantity);
	    } else {
	        throw new RuntimeException("Unknown product type for ID: " + productId);
	    }
	}



}
