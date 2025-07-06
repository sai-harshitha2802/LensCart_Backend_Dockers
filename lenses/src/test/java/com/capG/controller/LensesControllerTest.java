package com.capG.controller;

import com.capg.controller.LensesController;
import com.capg.dto.LensesDTO;
import com.capg.dto.ProductDTO;
import com.capg.service.LensesService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(LensesController.class)
public class LensesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LensesService lensesService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllLenses() throws Exception {
        LensesDTO lens = new LensesDTO("LENS-1", "BrandA", "Red", "img.jpg", "Lens 1", 500.0, 2, "Oval");
        Mockito.when(lensesService.getAllLenses()).thenReturn(List.of(lens));

        mockMvc.perform(get("/get-all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].lensId").value("LENS-1"));
    }

    @Test
    void testGetLensById() throws Exception {
        LensesDTO lens = new LensesDTO("LENS-2", "BrandA", "Red", "img.jpg", "Lens 1", 500.0, 2, "Oval");
        Mockito.when(lensesService.getLensById("LENS-2")).thenReturn(lens);

        mockMvc.perform(get("/byId").param("lensId", "LENS-2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Oval"));
    }

    @Test
    void testAddLens() throws Exception {
        LensesDTO lens = new LensesDTO("LENS-1", "RayBan", "image.jpg", "Round", "Black", 1500.0, 10, "Cool Lens");
        Mockito.when(lensesService.addLens(any())).thenReturn(lens);

        mockMvc.perform(post("/add-lense")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lens)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.lensId").value("LENS-1"));
    }

    @Test
    void testUpdateLens() throws Exception {
        String lensId = "LENS-4";
        LensesDTO updated = new LensesDTO(lensId, "RayBan", "image.jpg", "Round", "Black", 1500.0, 10, "Updated Lens");

        Mockito.when(lensesService.updateLens(Mockito.eq(lensId), any())).thenReturn(updated);

        mockMvc.perform(put("/" + lensId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Lens"));
    }

    @Test
    void testDeleteLens() throws Exception {
        Mockito.when(lensesService.deleteLens("LENS-5")).thenReturn(true);

        mockMvc.perform(delete("/LENS-5"))
                .andExpect(status().isNoContent());
    }

}
