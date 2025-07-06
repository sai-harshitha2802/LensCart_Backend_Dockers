package com.lenscart.controller;
 
import com.lenscart.dto.*;
import com.lenscart.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
 
import java.util.Arrays;
import java.util.List;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
class CustomerControllerTest {
 
    @Mock
    private CustomerService customerService;
 
    @InjectMocks
    private CustomerController customerController;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testViewAllProducts() {
        ProductDTO product1 = new ProductDTO();
        ProductDTO product2 = new ProductDTO();
 
        List<ProductDTO> products = Arrays.asList(product1, product2);
 
        when(customerService.viewAllProducts()).thenReturn(products);
 
        ResponseEntity<Object> response = customerController.viewAllProducts();
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(products, response.getBody());
    }
 
    @Test
    void testAddToCart() {
        CartRequestDTO request = new CartRequestDTO();
        CartResponseDTO responseDTO = new CartResponseDTO();
 
        when(customerService.addToCart(request)).thenReturn(responseDTO);
 
        ResponseEntity<Object> response = customerController.addToCart(request);
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(responseDTO, response.getBody());
    }
 
    @Test
    void testGetCartByCustomerId() {
        Long customerId = 1L;
        CartResponseDTO cart = new CartResponseDTO();
 
        when(customerService.getCartByCustomerId(customerId)).thenReturn(cart);
 
        ResponseEntity<Object> response = customerController.getCartByCustomerId(customerId);
 
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(cart, response.getBody());
    }
 
    @Test
    void testAddToCart_Exception() {
        CartRequestDTO request = new CartRequestDTO();
        when(customerService.addToCart(request)).thenThrow(new RuntimeException("Something went wrong"));
 
        ResponseEntity<Object> response = customerController.addToCart(request);
 
        assertEquals(500, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("Error adding item to cart"));
    }
}
 