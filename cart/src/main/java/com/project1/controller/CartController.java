package com.project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project1.dto.CartRequestDTO;
import com.project1.dto.CartResponseDTO;
import com.project1.service.CartService;

@RestController
@RequestMapping("/cart")

@CrossOrigin(origins = "http://localhost:4200")

public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public CartResponseDTO addToCart(@RequestBody CartRequestDTO request) {
        return cartService.addToCart(request);
    }

    @GetMapping("/{customerId}")
    public CartResponseDTO getCart(@PathVariable Long customerId) {
        return cartService.getCartByCustomerId(customerId);
    }

    @PutMapping("/{customerId}/update/{productId}/{quantity}")
    public CartResponseDTO updateQuantity(@PathVariable Long customerId,
                                          @PathVariable String productId,
                                          @PathVariable int quantity) {
        return cartService.updateQuantity(customerId, productId, quantity);
    }

    @DeleteMapping("/{customerId}/remove/{productId}")
    public CartResponseDTO removeProduct(@PathVariable Long customerId,
                                         @PathVariable String productId) {
        return cartService.removeProductFromCart(customerId, productId);
    }

    @DeleteMapping("/{customerId}/clear")
    public ResponseEntity<String> clearCart(@PathVariable Long customerId) {
        cartService.clearCart(customerId);
        return ResponseEntity.ok("Cart cleared successfully");
    }
}
