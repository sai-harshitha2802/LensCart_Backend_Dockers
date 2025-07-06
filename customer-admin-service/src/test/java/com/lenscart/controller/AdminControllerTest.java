package com.lenscart.controller;
 
import com.lenscart.dto.*;
import com.lenscart.feign.OrderServiceClient;
import com.lenscart.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
 
import java.util.Arrays;
import java.util.List;
 
import java.time.LocalDateTime;
import java.util.Collections;
 
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
class AdminControllerTest {
 
    @Mock
    private AdminService adminService;
 
    @Mock
    private OrderServiceClient orderService;
 
    @InjectMocks
    private AdminController adminController;
 
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testAddFrame() {
        FramesDTO frame = new FramesDTO(); // Add fields if needed
        when(adminService.addFrame(frame)).thenReturn(frame);
 
        ResponseEntity<FramesDTO> response = adminController.addFrame(frame);
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(frame, response.getBody());
        verify(adminService, times(1)).addFrame(frame);
    }
 
    @Test
    void testUpdateLens() {
        String lensId = "lens123";
        LensesDTO lens = new LensesDTO("lens123", "Crizal", "lens.jpg", "Round", "Clear", 1500.0, 5, "Crizal Clear Lens");
        when(adminService.updateLens(lensId, lens)).thenReturn(lens);
 
        ResponseEntity<LensesDTO> response = adminController.updateLens(lensId, lens);
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(lens, response.getBody());
        verify(adminService).updateLens(lensId, lens);
    }
 
    @Test
    void testDeleteGlassSuccess() {
        String glassId = "glass123";
 
        doNothing().when(adminService).deleteGlass(glassId);
 
        ResponseEntity<String> response = adminController.deleteGlass(glassId);
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Lens deleted successfully", response.getBody());
        verify(adminService).deleteGlass(glassId);
    }
 
    @Test
    void testGetAllOrders() {
 
OrderDTO order1 = new OrderDTO(1L, 101L, "123 Street", LocalDateTime.now(), 2999.99, "PAID", "SHIPPED", Collections.emptyList());
OrderDTO order2 = new OrderDTO(2L, 102L, "456 Avenue", LocalDateTime.now(), 3999.99, "PAID", "DELIVERED", Collections.emptyList());
 
        List<OrderDTO> orders = Arrays.asList(order1, order2);
 
        when(orderService.getAllOrders()).thenReturn(orders);
 
        ResponseEntity<List<OrderDTO>> response = adminController.getAllOrders();
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(orderService).getAllOrders();
    }
}