package com.project1.service;

import java.util.List;

import com.project1.dto.ProductDTO;
import com.project1.dto.SunGlassDTO;
import com.project1.entity.SunGlass;
import com.project1.exception.SunGlassNotFoundException;

public interface SunGlassService {
	
	SunGlassDTO addSunGlass(SunGlassDTO sunGlassDTO);
    List<SunGlassDTO> getAllSunGlasses();
    SunGlassDTO getSunGlassById(String id) throws SunGlassNotFoundException;
    SunGlassDTO updateSunGlass(String id, SunGlassDTO updatedSunGlassDTO) throws SunGlassNotFoundException;
    boolean deleteSunGlass(String id) throws SunGlassNotFoundException;
    
    public List<ProductDTO> getGlassAsProducts();
	void reduceStock(String id, int quantity);
	void increaseStock(String id, int quantity);

}
