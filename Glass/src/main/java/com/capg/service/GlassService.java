package com.capg.service;

import com.capg.dto.GlassDTO;
import com.capg.dto.ProductDTO;
import com.capg.exception.GlassNotFoundException;

import java.util.List;

public interface GlassService {
    GlassDTO addGlass(GlassDTO glassDTO);
    GlassDTO getGlassById(String glassId);
    List<GlassDTO> getAllGlasses();
    GlassDTO updateGlass(String glassId, GlassDTO glassDTO);
    boolean deleteGlass(String glassId) throws GlassNotFoundException;
    public List<ProductDTO> getGlassesAsProducts();
	void reduceStock(String glassId, int quantity);
	void increaseStock(String glassId, int quantity);
}
