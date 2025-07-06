package com.capg.service;

import java.util.List;

import com.capg.dto.ProductDTO;
import com.capg.dto.SunGlassDTO;
import com.capg.entity.SunGlass;
import com.capg.exception.SunGlassNotFoundException;

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
