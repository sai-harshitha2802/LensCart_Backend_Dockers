package com.project1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.project1.dto.LensesDTO;
import com.project1.dto.ProductDTO;
import com.project1.service.LensesService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
//@RequestMapping("/lenses")
@Tag(name = "Lens Controller", description = "APIs for managing lens")
@Validated
public class LensesController {

	@Autowired
	private LensesService lensesService;

	@Operation(summary = "Display all lenses", description = "Display all lenses")
	@GetMapping("/get-all")
	public ResponseEntity<List<LensesDTO>> getAllLenses() {
		List<LensesDTO> lenses = lensesService.getAllLenses();
		if (lenses.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(lenses, HttpStatus.OK);
	}

	@Operation(summary = "Display lenses by id", description = "Display lenses by id")
	@GetMapping("/byId")
	public ResponseEntity<LensesDTO> getLensById(@RequestParam String lensId) {
		LensesDTO lens = lensesService.getLensById(lensId);
		return new ResponseEntity<>(lens, HttpStatus.OK);
	}

	@Operation(summary = "Insert new lenses", description = "Insert new lenses")
	@PostMapping("/add-lense")
	public ResponseEntity<LensesDTO> addLens(@RequestBody @Valid LensesDTO lensDTO) {
		LensesDTO savedLens = lensesService.addLens(lensDTO);
		return new ResponseEntity<>(savedLens, HttpStatus.CREATED);
	}

	@Operation(summary = "Update existing lenses", description = "Update existing lenses")
	@PutMapping("/{lensId}")
	public ResponseEntity<LensesDTO> updateLens(@PathVariable String lensId, @Valid @RequestBody LensesDTO lensDTO) {
		LensesDTO updatedLens = lensesService.updateLens(lensId, lensDTO);
		return new ResponseEntity<>(updatedLens, HttpStatus.OK);
	}

	@Operation(summary = "Delete lenses", description = "Delete lenses by ID")
	@DeleteMapping("/{lensId}")
	public ResponseEntity<Void> deleteLens(@PathVariable String lensId) {
		boolean isDeleted = lensesService.deleteLens(lensId);
		if (isDeleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "Get lenses as ProductDTO for external services")
	@GetMapping("/product-lenses")
	public ResponseEntity<List<ProductDTO>> getLensesAsProducts() {
		List<ProductDTO> products = lensesService.getLensesAsProducts();
		return ResponseEntity.ok(products);
	}
	@PutMapping("/reduce-stock/{lensId}/{quantity}")
	@Operation(summary = "Reduce stock of a lens", description = "Reduces the stock of a lens after successful order")
	public ResponseEntity<String> reduceStock(
	        @PathVariable String lensId,
	        @PathVariable int quantity) {
	    lensesService.reduceStock(lensId, quantity);
	    return new ResponseEntity<>("Stock reduced for lens ID: " + lensId, HttpStatus.OK);
	}
	@PutMapping("/increase-stock/{lensId}/{quantity}")
	@Operation(summary = "Reduce stock of a lens", description = "Reduces the stock of a lens after successful order")
	public ResponseEntity<String> increaseStock(
	        @PathVariable String lensId,
	        @PathVariable int quantity) {
	    lensesService.increaseStock(lensId, quantity);
	    return new ResponseEntity<>("Stock reduced for lens ID: " + lensId, HttpStatus.OK);
	}
	

}
