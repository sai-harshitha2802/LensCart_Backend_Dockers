package com.project1.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project1.dto.GlassDTO;
import com.project1.dto.ProductDTO;
import com.project1.service.GlassService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@Tag(name = "Glass API", description = "APIs for managing glass products")
public class GlassController {

	@Autowired
	private GlassService glassService;

	@GetMapping("/all-glasses")
	@Operation(summary = "Get all glasses", description = "Retrieve all glasses available in the store")
	public ResponseEntity<List<GlassDTO>> getAllGlass() {
		List<GlassDTO> glasses = glassService.getAllGlasses();
		return ResponseEntity.ok(glasses);
	}

	@GetMapping("/{glassId}")
	@Operation(summary = "Get glass by ID", description = "Retrieve a single glass by its ID")
	public ResponseEntity<GlassDTO> getGlassById(@PathVariable String glassId) {
		GlassDTO glass = glassService.getGlassById(glassId);
		return ResponseEntity.ok(glass);
	}

	@PostMapping("/add-glasses")
	@Operation(summary = "Add a new glass", description = "Add a new glass product to the store")
	public ResponseEntity<GlassDTO> addGlass(@Valid @RequestBody GlassDTO glassDTO) {
		GlassDTO createdGlass = glassService.addGlass(glassDTO);
		return new ResponseEntity<>(createdGlass, HttpStatus.CREATED);
	}

	@PutMapping("/{glassId}")
	@Operation(summary = "Update a glass", description = "Update an existing glass product by ID")
	public ResponseEntity<GlassDTO> updateGlass(@PathVariable String glassId, @Valid @RequestBody GlassDTO glassDTO) {
		GlassDTO updatedGlass = glassService.updateGlass(glassId, glassDTO);
		return ResponseEntity.ok(updatedGlass);
	}

	@DeleteMapping("/{glassId}")
	@Operation(summary = "Delete glass by ID", description = "Delete a glass product using its ID")
	public ResponseEntity<String> deleteGlass(@PathVariable String glassId) {
		glassService.deleteGlass(glassId);
		return ResponseEntity.ok("Glass with ID " + glassId + " deleted successfully.");
	}

	@Operation(summary = "Get Glasses as ProductDTO for external services")
	@GetMapping("/product-glasses")
	public ResponseEntity<List<ProductDTO>> getLensesAsProducts() {
		List<ProductDTO> products = glassService.getGlassesAsProducts();
		return ResponseEntity.ok(products);
	}
	@PutMapping("/reduce-stock/{glassId}/{quantity}")
	@Operation(summary = "Reduce stock of a frame", description = "Reduces the stock of a glass after successful order")
	public ResponseEntity<String> reduceStock(
	        @PathVariable String glassId,
	        @PathVariable int quantity) {
	    glassService.reduceStock(glassId, quantity);
	    return new ResponseEntity<>("Stock reduced for frame ID: " + glassId, HttpStatus.OK);
	}
	@PutMapping("/increase-stock/{glassId}/{quantity}")
	@Operation(summary = "Reduce stock of a frame", description = "Reduces the stock of a glass after successful order")
	public ResponseEntity<String> incraeseStock(
	        @PathVariable String glassId,
	        @PathVariable int quantity) {
	    glassService.increaseStock(glassId, quantity);
	    return new ResponseEntity<>("Stock reduced for frame ID: " + glassId, HttpStatus.OK);
	}

}
