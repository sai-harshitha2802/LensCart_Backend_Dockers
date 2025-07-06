package com.capg.controller;
 
import com.capg.dto.SunGlassDTO;
import com.capg.service.SunGlassService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
 
import java.util.List;
 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 
@WebMvcTest(SunGlassController.class)
public class SunGlassControllerTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private SunGlassService service;
 
    @Autowired
    private ObjectMapper objectMapper;
 
    @Test
    void testAddSunGlass() throws Exception {
        SunGlassDTO dto = new SunGlassDTO(null, "Pilot", "Polaroid", 1500.0, "Silver", "Square", "Blue", "img.jpg", 5);
        SunGlassDTO savedDto = new SunGlassDTO("1", "Pilot", "Polaroid", 1500.0, "Silver", "Square", "Blue", "img.jpg", 5);
 
        Mockito.when(service.addSunGlass(Mockito.any())).thenReturn(savedDto);
 
        mockMvc.perform(post("/add-sunGlass")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.sunGlassId").value("1"));
    }
 
    @Test
    void testGetAllSunGlasses() throws Exception {
        SunGlassDTO dto = new SunGlassDTO("1", "Pilot", "Polaroid", 1500.0, "Silver", "Square", "Blue", "img.jpg", 5);
        Mockito.when(service.getAllSunGlasses()).thenReturn(List.of(dto));
 
        mockMvc.perform(get("/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }
 
    @Test
    void testGetSunGlassById() throws Exception {
        SunGlassDTO dto = new SunGlassDTO("1", "Pilot", "Polaroid", 1500.0, "Silver", "Square", "Blue", "img.jpg", 5);
        Mockito.when(service.getSunGlassById("1")).thenReturn(dto);
 
        mockMvc.perform(get("/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sunGlassId").value("1"));
    }
 
    @Test
    void testDeleteSunGlass() throws Exception {
        Mockito.when(service.deleteSunGlass("1")).thenReturn(true);
 
        mockMvc.perform(delete("/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("SunGlass deleted successfully."));
    }
}
 
