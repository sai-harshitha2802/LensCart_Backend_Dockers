package com.capg.controller;

import com.capg.dto.FramesDTO;
import com.capg.dto.ProductDTO;
import com.capg.entity.Frames;
import com.capg.repository.FramesRepository;
import com.capg.service.FramesService;
import com.capg.service.FramesServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Tag(name = "Frame Controller", description = "APIs for managing frames")
@Validated
public class FrameController {

	@Autowired
	private FramesService frameService;


	// Get all frames
	@Operation(summary = "Get all frames", description = "Returns a list of all available frames")
	@GetMapping("/allframes")
	public ResponseEntity<List<FramesDTO>> getAllFrames() {
		List<FramesDTO> frames = frameService.getAllFrames();
		if (frames.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(frames, HttpStatus.OK);
	}

	// Get frame by ID
	@Operation(summary = "Get frame by ID", description = "Returns a frame by its ID")
	@GetMapping("/{frameId}")
	public ResponseEntity<FramesDTO> getFrameById(@PathVariable String frameId) {
		FramesDTO frame = frameService.getFrameById(frameId);
		if (frame != null) {
			return new ResponseEntity<>(frame, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Add a new frame
	@Operation(summary = "Add a new frame", description = "Adds a new frame to the system")
	@PostMapping("/add-frame")
	public ResponseEntity<FramesDTO> addFrame(@Valid @RequestBody FramesDTO frameDTO) {
		FramesDTO savedFrame = frameService.addFrame(frameDTO);
		return new ResponseEntity<>(savedFrame, HttpStatus.CREATED);
	}

	// Update an existing frame
	@Operation(summary = "Update an existing frame", description = "Updates a frame by its ID")
	@PutMapping("/update-frame/{frameId}")
	public ResponseEntity<FramesDTO> updateFrame(@PathVariable String frameId, @Valid @RequestBody FramesDTO frameDTO) {
		FramesDTO updatedFrame = frameService.updateFrame(frameId, frameDTO);
		if (updatedFrame != null) {
			return new ResponseEntity<>(updatedFrame, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Delete a frame by ID
	@Operation(summary = "Delete a frame", description = "Deletes a frame by its ID")
	@DeleteMapping("/{frameId}")
	public ResponseEntity<String> deleteFrame(@PathVariable String frameId) {
		boolean isDeleted = frameService.deleteFrame(frameId);
		if (isDeleted) {
			return new ResponseEntity<>("Frame with ID " + frameId + " deleted successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Frame with ID " + frameId + " not found", HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "Get lenses as ProductDTO for external services")
	@GetMapping("/product-frames")
	public ResponseEntity<List<ProductDTO>> getFramesAsProducts() {
		List<ProductDTO> products = frameService.getFramesAsProducts();
		return ResponseEntity.ok(products);
	}
	@PutMapping("/reduce-stock/{frameId}/{quantity}")
	@Operation(summary = "Reduce stock of a frame", description = "Reduces the stock of a frame after successful order")
	public ResponseEntity<String> reduceStock(
	        @PathVariable String frameId,
	        @PathVariable int quantity) {
	    frameService.reduceStock(frameId, quantity);
	    return new ResponseEntity<>("Stock reduced for frame ID: " + frameId, HttpStatus.OK);
	}
	@PutMapping("/increase-stock/{frameId}/{quantity}")
	@Operation(summary = "Increase stock of a frame", description = "Increase the stock of a frame after successful order")
	public ResponseEntity<String> increaseStock(
	        @PathVariable String frameId,
	        @PathVariable int quantity) {
	    frameService.increaseStock(frameId, quantity);
	    return new ResponseEntity<>("Stock Increased for frame ID: " + frameId, HttpStatus.OK);
	}



}
