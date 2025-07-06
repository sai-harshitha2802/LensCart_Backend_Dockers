package com.capg.controller;

import com.capg.dto.CartRequestDTO;
import com.capg.dto.CartResponseDTO;
import com.capg.dto.CartItemDTO;
import com.capg.service.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddToCart() throws Exception {
        CartRequestDTO request = new CartRequestDTO();
        request.setCustomerId(1L);
        request.setProductId("LENS-1");
        request.setQuantity(2);

        CartItemDTO item = new CartItemDTO();
        item.setProductId("LENS-1");
        item.setQuantity(2);
        item.setPrice(500.0);

        CartResponseDTO response = new CartResponseDTO();
        response.setCartId(101L);
        response.setCustomerId(1L);
        response.setTotalAmount(1000.0);
        response.setCartItems(List.of(item));

        when(cartService.addToCart(any(CartRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/cart/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(1))
                .andExpect(jsonPath("$.totalAmount").value(1000.0))
                .andExpect(jsonPath("$.cartItems[0].productId").value("LENS-1"));
    }

    @Test
    void testGetCart() throws Exception {
        CartItemDTO item = new CartItemDTO();
        item.setProductId("FRAME-1");
        item.setQuantity(1);
        item.setPrice(1500.0);

        CartResponseDTO response = new CartResponseDTO();
        response.setCartId(102L);
        response.setCustomerId(2L);
        response.setTotalAmount(1500.0);
        response.setCartItems(List.of(item));

        when(cartService.getCartByCustomerId(2L)).thenReturn(response);

        mockMvc.perform(get("/cart/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(2))
                .andExpect(jsonPath("$.cartItems[0].productId").value("FRAME-1"));
    }

    @Test
    void testUpdateQuantity() throws Exception {
        CartItemDTO item = new CartItemDTO();
        item.setProductId("LENS-2");
        item.setQuantity(3);
        item.setPrice(300.0);

        CartResponseDTO response = new CartResponseDTO();
        response.setCartId(103L);
        response.setCustomerId(3L);
        response.setTotalAmount(900.0);
        response.setCartItems(List.of(item));

        when(cartService.updateQuantity(3L, "LENS-2", 3)).thenReturn(response);

        mockMvc.perform(put("/cart/3/update/LENS-2/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalAmount").value(900.0))
                .andExpect(jsonPath("$.cartItems[0].quantity").value(3));
    }

    @Test
    void testRemoveProductFromCart() throws Exception {
        CartResponseDTO response = new CartResponseDTO();
        response.setCartId(104L);
        response.setCustomerId(4L);
        response.setTotalAmount(0.0);
        response.setCartItems(List.of());

        when(cartService.removeProductFromCart(4L, "FRAME-2")).thenReturn(response);

        mockMvc.perform(delete("/cart/4/remove/FRAME-2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cartItems").isEmpty());
    }

    @Test
    void testClearCart() throws Exception {
        mockMvc.perform(delete("/cart/5/clear"))
                .andExpect(status().isOk());
    }
}
