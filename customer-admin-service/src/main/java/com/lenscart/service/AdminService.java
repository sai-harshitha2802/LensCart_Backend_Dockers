package com.lenscart.service;

import com.lenscart.dto.FramesDTO;
import com.lenscart.dto.GlassDTO;
import com.lenscart.dto.ProductDTO;
import com.lenscart.dto.SunGlassDTO;
import com.lenscart.dto.LensesDTO;

public interface AdminService {
    boolean loginAdmin(String email, String password);
    
    FramesDTO addFrame(FramesDTO framesDTO);
    FramesDTO updateFrame(String frameId, FramesDTO framesDTO);
    void deleteFrame(String frameId);

    
    LensesDTO addLens(LensesDTO lensesDTO);
    LensesDTO updateLens(String lensId, LensesDTO lensesDTO);
    boolean deleteLens(String lensId);
    
   GlassDTO addGlass(GlassDTO glassDTO);
   GlassDTO updateGlass(String glassId, GlassDTO glassDTO);
    void deleteGlass(String glassId);
    
    
    SunGlassDTO addSunGlass(SunGlassDTO sunglassDTO);
    SunGlassDTO updateSunGlass(String sunglassId, SunGlassDTO sunglassDTO);
     void deleteSunGlass(String sunglassId);

    
    String updateOrder(Long orderId, String status);
}
