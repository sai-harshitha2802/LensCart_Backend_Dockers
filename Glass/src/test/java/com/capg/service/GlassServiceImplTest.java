package com.capg.service;
 
import com.capg.dto.GlassDTO;
import com.capg.dto.ProductDTO;
import com.capg.entity.Glass;
import com.capg.exception.GlassNotFoundException;
import com.capg.repository.GlassRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
 
import java.util.*;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
public class GlassServiceImplTest {
 
    @Mock
    private GlassRepository glassRepository;
 
    @Mock
    private ModelMapper modelMapper;
 
    @InjectMocks
    private GlassServiceImpl glassService;
 
    private Glass glass;
    private GlassDTO glassDTO;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
 
        glass = new Glass();
        glass.setGlassId("GLASS-1");
        glass.setGlassName("Cool Glass");
        glass.setBrand("Rayban");
        glass.setGlassImage("img.jpg");
        glass.setPowerRange(1.5);
        glass.setType("Reading");
        glass.setPrice(1000.0);
        glass.setQuantity(5);
 
        glassDTO = new GlassDTO(
                "GLASS-1", "Rayban", "img.jpg", "Cool Glass", 1.5,
                "Reading", 1000.0, 5
        );
    }
 
    @Test
    void testAddGlass() {
        when(modelMapper.map(glassDTO, Glass.class)).thenReturn(glass);
        when(glassRepository.save(glass)).thenReturn(glass);
        when(modelMapper.map(glass, GlassDTO.class)).thenReturn(glassDTO);
 
        GlassDTO result = glassService.addGlass(glassDTO);
 
        assertEquals("Cool Glass", result.getGlassName());
        verify(glassRepository, times(1)).save(glass);
    }
 
    @Test
    void testGetGlassById_Success() {
        when(glassRepository.findById("GLASS-1")).thenReturn(Optional.of(glass));
        when(modelMapper.map(glass, GlassDTO.class)).thenReturn(glassDTO);
 
        GlassDTO result = glassService.getGlassById("GLASS-1");
 
        assertEquals("Cool Glass", result.getGlassName());
    }
 
    @Test
    void testGetGlassById_NotFound() {
        when(glassRepository.findById("GLASS-1")).thenReturn(Optional.empty());
 
        assertThrows(GlassNotFoundException.class, () -> glassService.getGlassById("GLASS-1"));
    }
 
    @Test
    void testGetAllGlasses() {
        List<Glass> glassList = List.of(glass);
        when(glassRepository.findAll()).thenReturn(glassList);
        when(modelMapper.map(glass, GlassDTO.class)).thenReturn(glassDTO);
 
        List<GlassDTO> result = glassService.getAllGlasses();
 
        assertEquals(1, result.size());
        assertEquals("GLASS-1", result.get(0).getGlassId());
    }
 
    @Test
    void testUpdateGlass_Success() {
        when(glassRepository.findById("GLASS-1")).thenReturn(Optional.of(glass));
        when(glassRepository.save(glass)).thenReturn(glass);
        when(modelMapper.map(glassDTO, Glass.class)).thenReturn(glass);
        when(modelMapper.map(glass, GlassDTO.class)).thenReturn(glassDTO);
 
        GlassDTO result = glassService.updateGlass("GLASS-1", glassDTO);
 
        assertEquals("GLASS-1", result.getGlassId());
        verify(glassRepository).save(glass);
    }
 
    @Test
    void testUpdateGlass_NotFound() {
        when(glassRepository.findById("GLASS-1")).thenReturn(Optional.empty());
 
        assertThrows(GlassNotFoundException.class, () -> glassService.updateGlass("GLASS-1", glassDTO));
    }
 
    @Test
    void testDeleteGlass_Success() {
        when(glassRepository.existsById("GLASS-1")).thenReturn(true);
 
        boolean result = glassService.deleteGlass("GLASS-1");
 
        assertTrue(result);
        verify(glassRepository).deleteById("GLASS-1");
    }
 
    @Test
    void testDeleteGlass_NotFound() {
        when(glassRepository.existsById("GLASS-1")).thenReturn(false);
 
        assertThrows(GlassNotFoundException.class, () -> glassService.deleteGlass("GLASS-1"));
    }
 
    @Test
    void testGetGlassesAsProducts() {
        when(glassRepository.findAll()).thenReturn(List.of(glass));
        when(modelMapper.map(glass, GlassDTO.class)).thenReturn(glassDTO);
 
        List<ProductDTO> result = glassService.getGlassesAsProducts();
 
        assertEquals(1, result.size());
        assertEquals("Cool Glass", result.get(0).getName());
        assertTrue(result.get(0).getDescription().contains("Rayban"));
    }
}