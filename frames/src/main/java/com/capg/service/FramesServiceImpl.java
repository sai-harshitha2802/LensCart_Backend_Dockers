package com.capg.service;

import com.capg.dto.FramesDTO;


import com.capg.dto.ProductDTO;
import com.capg.entity.Frames;
import com.capg.exception.FrameNotFoundException; // Importing the custom exception
import com.capg.repository.FramesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FramesServiceImpl implements FramesService {

	@Autowired
	private FramesRepository framesRepo;

	@Autowired
	private ModelMapper modelMapper;

	private static final Logger logger = LoggerFactory.getLogger(FramesServiceImpl.class);

	// Fetch all frames and return a list of FramesDTO
	@Override
	public List<FramesDTO> getAllFrames() {
		logger.info("Fetching all available frames");
		List<Frames> framesList = framesRepo.findAll();
		return framesList.stream().map(frame -> modelMapper.map(frame, FramesDTO.class)).collect(Collectors.toList());
	}

	// Fetch a frame by ID and return it as FramesDTO
	@Override
	public FramesDTO getFrameById(String frameId) throws FrameNotFoundException {
		logger.info("Fetching frame by ID: {}", frameId);

		// Fetch the frame by its ID from the repository
		Frames frame = framesRepo.findById(frameId)
				.orElseThrow(() -> new FrameNotFoundException("Frame with ID " + frameId + " not found"));

		// Use the mapper to convert the entity to DTO
		FramesDTO frameDTO = modelMapper.map(frame, FramesDTO.class);

		logger.info("Frame found with ID: {}", frameId);
		return frameDTO;
	}
	

	// Add a new frame and return the added frame as FramesDTO
	@Override
	public FramesDTO addFrame(FramesDTO frameDTO) {
		logger.info("Adding new frame: {}", frameDTO.getFrameName());

		// Saving the entity to the database

		Frames frame = modelMapper.map(frameDTO, Frames.class);
		Frames savedFrame = framesRepo.save(frame);
		logger.info("Frame added successfully with ID: {}", savedFrame.getFrameId());
		return modelMapper.map(savedFrame, FramesDTO.class); // returning dto instead of entity

	}

	// Update an existing frame based on ID and return the updated frame as
	// FramesDTO
	@Override
	public FramesDTO updateFrame(String frameId, FramesDTO frameDTO) {
		logger.info("Updating frame with ID: {}", frameId);
		Frames existingFrameOpt = framesRepo.findById(frameId)
				.orElseThrow(() -> new FrameNotFoundException("Frame with ID " + frameId + " not found"));
		String existingId = existingFrameOpt.getFrameId();

		modelMapper.map(frameDTO, existingFrameOpt); //Maps the new DTO values to the existing entity.Does not replace the object, just updates its fields.
		existingFrameOpt.setFrameId(frameId); //Ensure Frame ID is Not Changed

		// Saving the updated entity
		Frames updatedFrame = framesRepo.save(existingFrameOpt);
		logger.info("Frame updated successfully with ID: {}", updatedFrame.getFrameId());

		return modelMapper.map(updatedFrame, FramesDTO.class);
	}

	// Delete a frame by ID and return true if successful, false otherwise
	@Override
	public boolean deleteFrame(String frameId) throws FrameNotFoundException {
		if (!framesRepo.existsById(frameId)) {
			logger.warn("frame with ID {} not found. Delete failed", frameId);
			throw new FrameNotFoundException("Frame with ID " + frameId + " not found");
		}
		framesRepo.deleteById(frameId);
		logger.info("Frame deleted with ID: {}", frameId);
		return true;
	}
	

	public List<ProductDTO> getFramesAsProducts() {
		return getAllFrames().stream().map(frames -> {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(frames.getFrameId());
			productDTO.setBrand(frames.getBrand());
			productDTO.setPrice(frames.getPrice());
			String description = String.format("Colour: %s, ShapeOptions: %s, frameName: %s", frames.getColor(),
					frames.getShape(), frames.getFrameName());
			productDTO.setDescription(description);
			productDTO.setQuantity(frames.getQuantity());
			productDTO.setImageUrl(frames.getImageUrl());
			return productDTO;

		}).collect(Collectors.toList());
	}
	
	@Override
	public void reduceStock(String frameId, int quantity) {
	    Frames frame = framesRepo.findById(frameId)
	            .orElseThrow(() -> new FrameNotFoundException("Frame with ID " + frameId + " not found"));

	    if (quantity <= 0) {
	        throw new IllegalArgumentException("Quantity must be greater than 0");
	    }

	    if (frame.getQuantity() < quantity) {
	        throw new RuntimeException("Insufficient stock for frame ID: " + frameId);
	    }

	    frame.setQuantity(frame.getQuantity() - quantity);
	    framesRepo.save(frame);
	}
	@Override
	public void increaseStock(String frameId, int quantity) {
	    Frames frame = framesRepo.findById(frameId)
	            .orElseThrow(() -> new FrameNotFoundException("Frame with ID " + frameId + " not found"));
	    
	    frame.setQuantity(frame.getQuantity() + quantity);
	    framesRepo.save(frame);
	}   

}
