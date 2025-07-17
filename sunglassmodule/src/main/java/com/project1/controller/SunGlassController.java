package com.project1.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.project1.dto.ProductDTO;
import com.project1.dto.SunGlassDTO;
import com.project1.exception.SunGlassNotFoundException;
import com.project1.service.SunGlassService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
//@RequestMapping("/sunglasses")
@Tag(name = "Sunglasses Controller", description = "APIs for managing sunglasses")
@Validated // Enables validation for method-level constraints
public class SunGlassController {
	private static final Logger logger = LoggerFactory.getLogger(SunGlassController.class);

	@Autowired
	private SunGlassService sunGlassService;

	@PostMapping("/add-sunGlass")
	@Operation(summary = "Add a new Sunglass", description = "Adds a new sunglass to the inventory")
	public ResponseEntity<SunGlassDTO> addSunGlass(@Valid @RequestBody SunGlassDTO sunGlassDTO) {
		logger.info("Received request to add SunGlass: {}", sunGlassDTO.getSunGlassName());
		SunGlassDTO savedSunGlass = sunGlassService.addSunGlass(sunGlassDTO);
		return new ResponseEntity<>(savedSunGlass, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	@Operation(summary = "Get all Sunglasses", description = "Fetches all sunglasses from the database")
	public ResponseEntity<List<SunGlassDTO>> getAllSunGlasses() {
		logger.info("Fetching all SunGlasses");
		List<SunGlassDTO> sunglasses = sunGlassService.getAllSunGlasses();
		return new ResponseEntity<>(sunglasses, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get Sunglass by ID", description = "Fetches details of a sunglass using its ID")
	public ResponseEntity<SunGlassDTO> getSunGlassById(
			@Parameter(description = "ID of the Sunglass to fetch", in = ParameterIn.PATH, required = true) @PathVariable String id)
			throws SunGlassNotFoundException {
		logger.info("Fetching SunGlass with ID: {}", id);
		SunGlassDTO sunGlassDTO = sunGlassService.getSunGlassById(id);
		return new ResponseEntity<>(sunGlassDTO, HttpStatus.OK);
	}

	@PutMapping("/update-sunGlass/{id}")
	@Operation(summary = "update Sunglass", description = "Updates an existing sunglass in the database")
	public ResponseEntity<SunGlassDTO> updateSunGlass(
			@Parameter(description = "ID of the Sunglass to update", in = ParameterIn.PATH, required = true) @PathVariable String id,
			@Valid @RequestBody SunGlassDTO sunGlassDTO) throws SunGlassNotFoundException {
		logger.info("Updating SunGlass with ID: {}", id);
		SunGlassDTO updatedSunGlass = sunGlassService.updateSunGlass(id, sunGlassDTO);
		return new ResponseEntity<>(updatedSunGlass, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete Sunglass", description = "Deletes a sunglass from the inventory")
	public ResponseEntity<String> deleteSunGlass(
			@Parameter(description = "ID of the Sunglass to delete", in = ParameterIn.PATH, required = true) @PathVariable String id)
			throws SunGlassNotFoundException {
		logger.info("Deleting SunGlass with ID: {}", id);

		sunGlassService.deleteSunGlass(id); // If it doesn't exist, service throws an exception

		logger.info("SunGlass deleted successfully with ID: {}", id);
		return new ResponseEntity<>("SunGlass deleted successfully.", HttpStatus.OK);
	}

	@Operation(summary = "Get lenses as ProductDTO for external services")
	@GetMapping("/product-sunglass")
	public ResponseEntity<List<ProductDTO>> getSunGlassAsProducts() {
		List<ProductDTO> products = sunGlassService.getGlassAsProducts();
		return ResponseEntity.ok(products);
	}
	@PutMapping("/reduce-stock/{id}/{quantity}")
	@Operation(summary = "Reduce stock of a frame", description = "Reduces the stock of a frame after successful order")
	public ResponseEntity<String> reduceStock(
	        @PathVariable String id,
	        @PathVariable int quantity) {
		sunGlassService.reduceStock(id, quantity);
	    return new ResponseEntity<>("Stock reduced for frame ID: " + id, HttpStatus.OK);
	}
	@PutMapping("/increase-stock/{id}/{quantity}")
	@Operation(summary = "Reduce stock of a frame", description = "Reduces the stock of a frame after successful order")
	public ResponseEntity<String> increaseStock(
	        @PathVariable String id,
	        @PathVariable int quantity) {
		sunGlassService.increaseStock(id, quantity);
	    return new ResponseEntity<>("Stock reduced for frame ID: " + id, HttpStatus.OK);
	}
}
