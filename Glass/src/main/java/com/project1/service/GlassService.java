package com.project1.service;

import java.util.List;

import com.project1.dto.GlassDTO;
import com.project1.dto.ProductDTO;
import com.project1.exception.GlassNotFoundException;

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
