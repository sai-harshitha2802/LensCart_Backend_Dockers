package com.capG.service;

import com.capg.dto.LensesDTO;
import com.capg.dto.ProductDTO;
import com.capg.entity.Lenses;
import com.capg.exception.LensNotFoundException;
import com.capg.repository.LensesRepository;
import com.capg.service.LensesService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LensesServiceTest {

    @Mock
    private LensesRepository repo;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private LensesService service;

    @Test
    void testGetAllLenses() {
        Lenses lens = new Lenses();
        lens.setLensId("LENS-1");
        lens.setName("Cool Lens");

        when(repo.findAll()).thenReturn(List.of(lens));
        when(mapper.map(any(Lenses.class), eq(LensesDTO.class))).thenReturn(new LensesDTO("LENS-1", "RayBan", "image.jpg", "Round", "Black", 1500.0, 10, "Cool Lens"));

        List<LensesDTO> result = service.getAllLenses();

        assertEquals(1, result.size());
        assertEquals("Cool Lens", result.get(0).getName());
    }

    @Test
    void testGetLensById_Found() {
        Lenses lens = new Lenses();
        lens.setLensId("LENS-1");
        

        when(repo.findById("LENS-1")).thenReturn(Optional.of(lens));
        when(mapper.map(any(Lenses.class), eq(LensesDTO.class))).thenReturn(new LensesDTO("LENS-1", "RayBan", "image.jpg", "Round", "Black", 1500.0, 10, "Blue Lens"));

        LensesDTO dto = service.getLensById("LENS-1");

        assertEquals("Blue Lens", dto.getName());
    }

    @Test
    void testGetLensById_NotFound() {
        when(repo.findById("LENS-404")).thenReturn(Optional.empty());
        assertThrows(LensNotFoundException.class, () -> service.getLensById("LENS-404"));
    }

    @Test
    void testAddLens() {
        LensesDTO input = new LensesDTO("LENS-1", "RayBan", "image.jpg", "Round", "Black", 1500.0, 10, "Cool Lens");
        Lenses entity = new Lenses();
        entity.setLensId("LENS-1");

        when(mapper.map(input, Lenses.class)).thenReturn(entity);
        when(repo.save(entity)).thenReturn(entity);
        when(mapper.map(entity, LensesDTO.class)).thenReturn(new LensesDTO("LENS-1", "RayBan", "image.jpg", "Round", "Black", 1500.0, 10, "Cool Lens"));

        LensesDTO result = service.addLens(input);

        assertEquals("LENS-1", result.getLensId());
    }

    @Test
    void testUpdateLens_Found() {
        Lenses existing = new Lenses();
        existing.setLensId("LENS-1");

        LensesDTO update = new LensesDTO("LENS-1", "RayBan", "image.jpg", "Round", "Black", 1500.0, 10, "Cool Lens");
        Lenses updatedEntity = new Lenses();
        updatedEntity.setLensId("LENS-1");
        updatedEntity.setName("Updated Lens");

        when(repo.findById("LENS-1")).thenReturn(Optional.of(existing));
        when(mapper.map(update, Lenses.class)).thenReturn(updatedEntity);
        when(repo.save(updatedEntity)).thenReturn(updatedEntity);
        when(mapper.map(updatedEntity, LensesDTO.class)).thenReturn(new LensesDTO("LENS-1", "RayBan", "image.jpg", "Round", "Black", 1500.0, 10, "Updated Lens"));

        LensesDTO result = service.updateLens("LENS-1", update);

        assertEquals("Updated Lens", result.getName());
    }

    @Test
    void testUpdateLens_NotFound() {
        when(repo.findById("LENS-404")).thenReturn(Optional.empty());
        assertThrows(LensNotFoundException.class, () -> service.updateLens("LENS-404", new LensesDTO()));
    }

    @Test
    void testDeleteLens_Success() {
        Lenses lens = new Lenses();
        lens.setLensId("LENS-88");

        when(repo.findById("LENS-88")).thenReturn(Optional.of(lens));

        boolean result = service.deleteLens("LENS-88");

        assertTrue(result);
        verify(repo).deleteById("LENS-88");
    }

    @Test
    void testDeleteLens_NotFound() {
        when(repo.findById("LENS-999")).thenReturn(Optional.empty());
        assertThrows(LensNotFoundException.class, () -> service.deleteLens("LENS-999"));
    }

//    @Test
//    void testGetLensesAsProducts() {
//        Lenses lens = new Lenses();
//        lens.setLensId("LENS-1");
//        lens.setName("RayBan");
//        lens.setShape("Round");
//        lens.setColour("Black");
//        lens.setPrice(1999.99);
//        lens.setQuantity(10);
//
//        LensesDTO dto = new LensesDTO("LENS-1", "RayBan", "image.jpg", "Round", "Black", 1500.0, 10, "Cool Lens");
//        when(repo.findAll()).thenReturn(List.of(lens));
//        when(mapper.map(any(Lenses.class), eq(LensesDTO.class))).thenReturn(dto);
//
//        List<ProductDTO> result = service.getLensesAsProducts();
//
//        assertEquals(1, result.size());
//        assertEquals("Sun Lens", result.get(0).getName());
//        assertEquals("Shape: Round, Colour: Black", result.get(0).getDescription());
//    }
}
