package com.capg.controller;
 
import com.capg.dto.GlassDTO;
import com.capg.dto.ProductDTO;
import com.capg.service.GlassService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
 
import java.util.List;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 
@WebMvcTest(GlassController.class)
public class GlassControllerTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private GlassService glassService;
 
    @Autowired
    private ObjectMapper objectMapper;
 
    private GlassDTO getSampleGlass() {
        return new GlassDTO("GLASS-1", "RayBan", "http://image.jpg", "Aviator", 1.25, "Prescription", 1999.99, 10);
    }
 
    private ProductDTO getSampleProduct() {
        return new ProductDTO("GLASS-1", 1999.99, "Stylish glass", "Aviator", 10, "http://image.jpg");
    }
 
    @Test
    void testGetAllGlass() throws Exception {
        Mockito.when(glassService.getAllGlasses()).thenReturn(List.of(getSampleGlass()));
 
        mockMvc.perform(get("/all-glasses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].glassId").value("GLASS-1"));
    }
 
    @Test
    void testGetGlassById() throws Exception {
        Mockito.when(glassService.getGlassById("GLASS-1")).thenReturn(getSampleGlass());
 
        mockMvc.perform(get("/GLASS-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.glassId").value("GLASS-1"));
    }
 
    @Test
    void testAddGlass() throws Exception {
        GlassDTO glassDTO = getSampleGlass();
 
        Mockito.when(glassService.addGlass(any(GlassDTO.class))).thenReturn(glassDTO);
 
        mockMvc.perform(post("/add-glasses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(glassDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.glassId").value("GLASS-1"));
    }
 
    @Test
    void testUpdateGlass() throws Exception {
        GlassDTO updatedGlass = getSampleGlass();
        updatedGlass.setBrand("Oakley");
 
        Mockito.when(glassService.updateGlass(eq("GLASS-1"), any(GlassDTO.class))).thenReturn(updatedGlass);
 
        mockMvc.perform(put("/update-glasses/GLASS-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedGlass)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand").value("Oakley"));
    }
 
    @Test
    void testDeleteGlass() throws Exception {
    	
    	Mockito.when(glassService.deleteGlass("GLASS-1")).thenReturn(true);
 
        mockMvc.perform(delete("/GLASS-1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Glass with ID GLASS-1 deleted successfully."));
    }
 
    @Test
    void testGetGlassesAsProducts() throws Exception {
        Mockito.when(glassService.getGlassesAsProducts()).thenReturn(List.of(getSampleProduct()));
 
        mockMvc.perform(get("/product-glasses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("GLASS-1"));
    }
}                              