package com.capg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capg.dto.ProductDTO;
import com.capg.feign.FramesClient;
import com.capg.feign.LensClient;
import com.capg.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Operation(summary = "Get All Products", description = "Fetches all products including lenses")
	@GetMapping("/all")

	public ResponseEntity<List<ProductDTO>> getAllProducts() {
		List<ProductDTO> products = productService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable String id) {
		ProductDTO product = productService.getProductById(id);
		if (product == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(product);
	}
	@PutMapping("/reduce-stock/{id}")
	public ResponseEntity<String> reduceStock(@PathVariable String id, @RequestParam int quantity) {
	    productService.reduceStock(id, quantity);
	    return ResponseEntity.ok("Stock updated");
	}
	@PutMapping("/increase-stock/{id}")
	public ResponseEntity<String> increaseStock(@PathVariable String id, @RequestParam int quantity) {
	    productService.increaseStock(id, quantity);
	    return ResponseEntity.ok("Stock updated");
	}

}
