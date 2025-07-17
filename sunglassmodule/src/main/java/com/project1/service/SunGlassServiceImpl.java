package com.project1.service;
 
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project1.dto.ProductDTO;
import com.project1.dto.SunGlassDTO;
import com.project1.entity.SunGlass;
import com.project1.exception.SunGlassNotFoundException;
import com.project1.repository.SunGlassRepository;
 
@Service
public class SunGlassServiceImpl implements SunGlassService {
	private static final Logger logger = LoggerFactory.getLogger(SunGlassServiceImpl.class);
 
	@Autowired
	private SunGlassRepository sunGlassRepo;
 
	@Override
	public SunGlassDTO addSunGlass(SunGlassDTO sunGlassDTO) {
		logger.info("Adding new SunGlass: {}", sunGlassDTO.getSunGlassName());
 
		// Convert DTO to entity and save it to the repository
		SunGlass sunGlass = convertToEntity(sunGlassDTO);
		SunGlass savedSunGlass = sunGlassRepo.save(sunGlass);
 
		logger.info("SunGlass added successfully with ID: {}", savedSunGlass.getSunGlassId());
		return convertToDTO(savedSunGlass);
	}
 
	@Override
	public List<SunGlassDTO> getAllSunGlasses() {
		logger.info("Fetching all SunGlasses");
		// Retrieve all SunGlasses and convert them to DTOs
		return sunGlassRepo.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}
 
	@Override
	public SunGlassDTO getSunGlassById(String id) throws SunGlassNotFoundException {
		logger.info("Fetching SunGlass with ID: {}", id);
		// Fetch SunGlass by ID, throw exception if not found
		SunGlass sunGlass = sunGlassRepo.findById(id).orElseThrow(() -> {
			logger.error("SunGlass not found with ID: {}", id);
			return new SunGlassNotFoundException("SunGlass not found with ID: " + id);
		});
		return convertToDTO(sunGlass);
	}
 
	@Override
	public SunGlassDTO updateSunGlass(String id, SunGlassDTO updatedSunGlassDTO) throws SunGlassNotFoundException {
		logger.info("Updating SunGlass with ID: {}", id);
 
		// Fetch SunGlass by ID, throw exception if not found
		SunGlass sunGlass = sunGlassRepo.findById(id).orElseThrow(() -> {
			logger.error("SunGlass not found for update with ID: {}", id);
			return new SunGlassNotFoundException("SunGlass not found with ID: " + id);
		});
 
		// Update SunGlass entity with DTO data
		sunGlass.setSunGlassName(updatedSunGlassDTO.getSunGlassName());
		sunGlass.setBrand(updatedSunGlassDTO.getBrand());
		sunGlass.setPrice(updatedSunGlassDTO.getPrice());
		sunGlass.setFrameColor(updatedSunGlassDTO.getFrameColor());
		sunGlass.setFrameShape(updatedSunGlassDTO.getFrameShape());
		sunGlass.setGlassColor(updatedSunGlassDTO.getGlassColor());
		sunGlass.setBrand(updatedSunGlassDTO.getBrand());
		sunGlass.setImage(updatedSunGlassDTO.getImage());
 
		SunGlass updatedSunGlass = sunGlassRepo.save(sunGlass);
		logger.info("SunGlass updated successfully with ID: {}", id);
		return convertToDTO(updatedSunGlass);
	}
 
	@Override
	public boolean deleteSunGlass(String id) throws SunGlassNotFoundException {
		logger.info("Deleting SunGlass with ID: {}", id);
		// Check if SunGlass exists before deletion
		if (!sunGlassRepo.existsById(id)) {
			logger.error("SunGlass not found for deletion with ID: {}", id);
			throw new SunGlassNotFoundException("SunGlass not found with ID: " + id);
		}
		sunGlassRepo.deleteById(id);
		logger.info("SunGlass deleted successfully with ID: {}", id);
		return true;
	}
 
	// Convert SunGlassDTO to SunGlass entity
	private SunGlass convertToEntity(SunGlassDTO dto) {
		SunGlass sunGlass = new SunGlass();
		sunGlass.setSunGlassName(dto.getSunGlassName());
		sunGlass.setQuantity(dto.getQuantity());
		sunGlass.setPrice(dto.getPrice());
		sunGlass.setFrameColor(dto.getFrameColor());
		sunGlass.setFrameShape(dto.getFrameShape());
		sunGlass.setGlassColor(dto.getGlassColor());
		sunGlass.setBrand(dto.getBrand());
		sunGlass.setImage(dto.getImage());
		return sunGlass;
	}
 
	// Convert SunGlass entity to SunGlassDTO
	private SunGlassDTO convertToDTO(SunGlass sunGlass) {
//		return new SunGlassDTO(sunGlass.getSunGlassId(), sunGlass.getSunGlassName(), sunGlass.getQuantity(),sunGlass.getImage(),sunGlass.getBrand(),
//				sunGlass.getPrice(), sunGlass.getFrameColor(), sunGlass.getFrameShape(), sunGlass.getGlassColor());
		return new SunGlassDTO(sunGlass.getSunGlassId(),sunGlass.getSunGlassName(),sunGlass.getBrand(),sunGlass.getPrice(),sunGlass.getFrameColor(),sunGlass.getFrameShape(),sunGlass.getGlassColor(),sunGlass.getImage(),sunGlass.getQuantity());
	}
 
	public List<ProductDTO> getGlassAsProducts() {
		// Convert SunGlasses to ProductDTO for external use
		return getAllSunGlasses().stream().map(sunglass -> {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(sunglass.getSunGlassId());
			productDTO.setQuantity(sunglass.getQuantity());
			productDTO.setPrice(sunglass.getPrice());
			productDTO.setName(sunglass.getSunGlassName());
			// Formatted description using shape and color
			String description = String.format("Shape: %s, Colour: %s", sunglass.getFrameShape(),
					sunglass.getFrameColor());
			productDTO.setDescription(description);
			productDTO.setImageUrl(sunglass.getImage());
			return productDTO;
		}).collect(Collectors.toList());
	}
	
	@Override
	public void reduceStock(String id, int quantity) {
	    SunGlass frame = sunGlassRepo.findById(id)
	            .orElseThrow(() -> new SunGlassNotFoundException("Frame with ID " + id + " not found"));

	    if (quantity <= 0) {
	        throw new IllegalArgumentException("Quantity must be greater than 0");
	    }

	    if (frame.getQuantity() < quantity) {
	        throw new RuntimeException("Insufficient stock for frame ID: " + id);
	    }

	    frame.setQuantity(frame.getQuantity() - quantity);
	    sunGlassRepo.save(frame);
	}
	@Override
	public void increaseStock(String id, int quantity) {
	    SunGlass frame = sunGlassRepo.findById(id)
	            .orElseThrow(() -> new SunGlassNotFoundException("Frame with ID " + id + " not found"));
	    frame.setQuantity(frame.getQuantity() + quantity);
	    sunGlassRepo.save(frame);  
	}
}