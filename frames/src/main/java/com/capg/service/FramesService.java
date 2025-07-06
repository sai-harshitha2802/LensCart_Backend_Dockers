package com.capg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capg.dto.FramesDTO;
import com.capg.dto.ProductDTO;
import com.capg.entity.Frames;
import com.capg.exception.FrameNotFoundException;

@Service
public interface FramesService {

    // Add a new frame, taking a DTO and returning the DTO of the saved frame
    public FramesDTO addFrame(FramesDTO frameDto);

    // Get all frames, returning a list of DTOs
    public List<FramesDTO> getAllFrames();

    // Get a frame by its ID, returning the corresponding DTO
    public FramesDTO getFrameById(String frameId) throws FrameNotFoundException;

    // Delete a frame by ID and return a boolean to indicate success
    boolean deleteFrame(String frameId) throws FrameNotFoundException;

    // Update an existing frame based on ID and DTO, returning the updated DTO
    public FramesDTO updateFrame(String frameId, FramesDTO frameDto) throws FrameNotFoundException;

	public List<ProductDTO> getFramesAsProducts();

	void reduceStock(String frameId, int quantity);

	void increaseStock(String frameId, int quantity);
	

	
    
}
