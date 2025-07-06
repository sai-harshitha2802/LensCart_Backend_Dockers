package com.capg.service;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import com.capg.dto.SunGlassDTO;
import com.capg.entity.SunGlass;
import com.capg.exception.SunGlassNotFoundException;
import com.capg.repository.SunGlassRepository;
import com.capg.service.SunGlassServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import java.util.*;
 
public class SunGlassServiceImplTest {
 
    @InjectMocks
    private SunGlassServiceImpl service;
 
    @Mock
    private SunGlassRepository repo;
 
    private SunGlass sunGlass;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sunGlass = new SunGlass("1", "Aviator", "RayBan", 2000.0, "Black", "Round", "Grey", "image.jpg", 10);
    }
 
    @Test
    void testAddSunGlass() {
        when(repo.save(any(SunGlass.class))).thenReturn(sunGlass);
 
        SunGlassDTO dto = new SunGlassDTO(null, "Aviator", "RayBan", 2000.0, "Black", "Round", "Grey", "image.jpg", 10);
        SunGlassDTO result = service.addSunGlass(dto);
 
        assertNotNull(result);
        assertEquals("Aviator", result.getSunGlassName());
    }
 
    @Test
    void testGetAllSunGlasses() {
        when(repo.findAll()).thenReturn(List.of(sunGlass));
        List<SunGlassDTO> list = service.getAllSunGlasses();
        assertEquals(1, list.size());
    }
 
    @Test
    void testGetSunGlassById_Success() throws SunGlassNotFoundException {
        when(repo.findById("1")).thenReturn(Optional.of(sunGlass));
        SunGlassDTO dto = service.getSunGlassById("1");
        assertEquals("RayBan", dto.getBrand());
    }
 
    @Test
    void testGetSunGlassById_NotFound() {
        when(repo.findById("999")).thenReturn(Optional.empty());
        assertThrows(SunGlassNotFoundException.class, () -> service.getSunGlassById("999"));
    }
 
    @Test
    void testDeleteSunGlass_Success() throws SunGlassNotFoundException {
        when(repo.existsById("1")).thenReturn(true);
        boolean result = service.deleteSunGlass("1");
        assertTrue(result);
        verify(repo).deleteById("1");
    }
 
    @Test
    void testDeleteSunGlass_NotFound() {
        when(repo.existsById("1")).thenReturn(false);
        assertThrows(SunGlassNotFoundException.class, () -> service.deleteSunGlass("1"));
    }
}