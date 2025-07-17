package com.project1.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project1.dto.LensesDTO;
import com.project1.dto.ProductDTO;
import com.project1.entity.Lenses;
import com.project1.exception.LensNotFoundException;
import com.project1.repository.LensesRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LensesService {

	@Autowired
	private LensesRepository lensesRepo;

	@Autowired
	private ModelMapper modelMapper; // Inject ModelMapper

	private static final Logger logger = LoggerFactory.getLogger(LensesService.class);

	public List<LensesDTO> getAllLenses() {
		// Fetching all lenses
		List<Lenses> lensesList = lensesRepo.findAll();
		return lensesList.stream().map(lens -> modelMapper.map(lens, LensesDTO.class)) // Use ModelMapper here
				.collect(Collectors.toList());
	}

	public LensesDTO getLensById(String lensId) {
		// Fetching lens by ID
		Optional<Lenses> lensOptional = lensesRepo.findById(lensId);
		if (!lensOptional.isPresent()) {
			throw new LensNotFoundException("Lens not found for the provided ID: " + lensId);
		}
		Lenses lens = lensOptional.get();
		return modelMapper.map(lens, LensesDTO.class); // Use ModelMapper here
	}

	public LensesDTO addLens(LensesDTO lensDTO) {
		// Mapping DTO to Entity using ModelMapper
		Lenses lens = modelMapper.map(lensDTO, Lenses.class);
		// Ensure the ID is set to 0 for a new lens

		// Saving the lens to the database
		Lenses savedLens = lensesRepo.save(lens);
		logger.info("Lens added successfully with id {}", savedLens.getLensId());

		// Returning the saved lens as a DTO
		return modelMapper.map(savedLens, LensesDTO.class); // Use ModelMapper here
	}

	public LensesDTO updateLens(String lensId, LensesDTO lensDTO) {
		Optional<Lenses> existingLens = lensesRepo.findById(lensId);
		if (!existingLens.isPresent()) {
			throw new LensNotFoundException("Lens not found for the given ID: " + lensId);
		}

		// Mapping DTO to Entity using ModelMapper
		Lenses lens = modelMapper.map(lensDTO, Lenses.class);

		// Ensure lensId remains the same
		lens.setLensId(lensId);

		// Save the updated lens
		Lenses updatedLens = lensesRepo.save(lens);
		logger.info("Lens updated successfully with id {}", updatedLens.getLensId());

		// Convert to DTO and return
		return modelMapper.map(updatedLens, LensesDTO.class);
	}

	public boolean deleteLens(String lensId) {
		Optional<Lenses> lensOptional = lensesRepo.findById(lensId);
		if (!lensOptional.isPresent()) {
			throw new LensNotFoundException("No lens found with ID: " + lensId);
		}
		lensesRepo.deleteById(lensId);
		logger.info("Lens with ID {} deleted successfully", lensId);
		return true;
	}

	// ✅ Convert LensesDTO to ProductDTO
	public List<ProductDTO> getLensesAsProducts() {

		return getAllLenses().stream().map(lens -> {

			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(lens.getLensId());
			productDTO.setName(lens.getName());
			productDTO.setPrice(lens.getPrice());
			productDTO.setQuantity(lens.getQuantity());
			String description = String.format("Shape: %s, Colour: %s", lens.getShape(), lens.getColour());
			productDTO.setDescription(description); // Using lensImage field for description
			productDTO.setImageUrl(lens.getLensImage());

			return productDTO;
		}).collect(Collectors.toList());

	}
	

	public void reduceStock(String lensId, int quantity) {
	    Lenses lens = lensesRepo.findById(lensId)
	            .orElseThrow(() -> new LensNotFoundException("Frame with ID " + lensId + " not found"));

	    if (quantity <= 0) {
	        throw new IllegalArgumentException("Quantity must be greater than 0");
	    }

	    if (lens.getQuantity() < quantity) {
	        throw new RuntimeException("Insufficient stock for frame ID: " + lensId);
	    }

	    lens.setQuantity(lens.getQuantity() - quantity);
	    lensesRepo.save(lens);
	}
	public void increaseStock(String lensId, int quantity) {
	    Lenses lens = lensesRepo.findById(lensId)
	            .orElseThrow(() -> new LensNotFoundException("Frame with ID " + lensId + " not found"));
	    lens.setQuantity(lens.getQuantity() + quantity);
	    lensesRepo.save(lens);
	}
}
