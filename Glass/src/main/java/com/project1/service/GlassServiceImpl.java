package com.project1.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project1.dto.GlassDTO;
import com.project1.dto.ProductDTO;
import com.project1.entity.Glass;
import com.project1.exception.GlassNotFoundException;
import com.project1.repository.GlassRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GlassServiceImpl implements GlassService {

	@Autowired
	private GlassRepository glassRepository;

	@Autowired
	private ModelMapper modelMapper; // Injected from AppConfig

	// Add a new glass to the database
	@Override
	public GlassDTO addGlass(GlassDTO glassDTO) {
	    Glass glass = modelMapper.map(glassDTO, Glass.class); // Convert DTO to Entity
	    Glass savedGlass = glassRepository.save(glass);
	    return modelMapper.map(savedGlass, GlassDTO.class); // Convert back to DTO
	}

	// Retrieve a glass by its ID, throw exception if not found
	@Override
	public GlassDTO getGlassById(String glassId) {
	    Glass glass = glassRepository.findById(glassId)
	            .orElseThrow(() -> new GlassNotFoundException("Glass not found with ID: " + glassId));
	    return modelMapper.map(glass, GlassDTO.class); // Convert Entity to DTO
	}

	// Fetch all glasses from the database and convert to DTO list
	@Override
	public List<GlassDTO> getAllGlasses() {
	    List<Glass> glasses = glassRepository.findAll();
	    return glasses.stream().map(glass -> modelMapper.map(glass, GlassDTO.class))
	            .collect(Collectors.toList());
	}

	// Update existing glass details by ID
	@Override
	public GlassDTO updateGlass(String glassId, GlassDTO glassDTO) {
	    Glass existingGlass = glassRepository.findById(glassId)
	            .orElseThrow(() -> new GlassNotFoundException("Glass not found with ID: " + glassId));

	    // Preserve the original ID
	    String existingId = existingGlass.getGlassId();

	    // Map updated fields from DTO to entity (excluding ID)
	    modelMapper.map(glassDTO, existingGlass);

	    // Ensure the original ID remains unchanged
	    existingGlass.setGlassId(existingId);

	    Glass savedGlass = glassRepository.save(existingGlass);
	    return modelMapper.map(savedGlass, GlassDTO.class);
	}

	// Delete a glass by ID if it exists
	@Override
	public boolean deleteGlass(String glassId) throws GlassNotFoundException {
	    if (!glassRepository.existsById(glassId)) {
	        throw new GlassNotFoundException("Glass with ID " + glassId + " not found");
	    }
	    glassRepository.deleteById(glassId);
	    return true;
	}

	// Convert glasses to ProductDTO format for external services
	public List<ProductDTO> getGlassesAsProducts() {
	    return getAllGlasses().stream().map(glasses -> {
	        ProductDTO productDTO = new ProductDTO();
	        productDTO.setId(glasses.getGlassId());
	        productDTO.setName(glasses.getGlassName());
	        productDTO.setPrice(glasses.getPrice());
	        productDTO.setQuantity(glasses.getQuantity());
	        // Format a description using glass details
	        String description = String.format(" Range: %s, Type: %s, Brand: %s", 
	                glasses.getPowerRange(), glasses.getType(),glasses.getBrand());
	        productDTO.setDescription(description);
	        productDTO.setImageUrl(glasses.getGlassImage());
	        return productDTO;
	    }).collect(Collectors.toList());
	}
	@Override
	public void reduceStock(String glassId, int quantity) {
	    Glass glass = glassRepository.findById(glassId)
	            .orElseThrow(() -> new GlassNotFoundException("Frame with ID " + glassId + " not found"));

	    if (quantity <= 0) {
	        throw new IllegalArgumentException("Quantity must be greater than 0");
	    }

	    if (glass.getQuantity() < quantity) {
	        throw new RuntimeException("Insufficient stock for frame ID: " + glassId);
	    }

	    glass.setQuantity(glass.getQuantity() - quantity);
	    glassRepository.save(glass);
	}
	@Override
	public void increaseStock(String glassId, int quantity) {
	    Glass glass = glassRepository.findById(glassId)
	            .orElseThrow(() -> new GlassNotFoundException("Frame with ID " + glassId + " not found"));
	    
	    glass.setQuantity(glass.getQuantity() + quantity);
	    glassRepository.save(glass);
	}
}
