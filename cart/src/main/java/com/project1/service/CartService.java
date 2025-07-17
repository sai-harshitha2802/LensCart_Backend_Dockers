package com.project1.service;

import java.util.List;
import java.util.Optional;

import com.project1.dto.CartRequestDTO;
import com.project1.dto.CartResponseDTO;
import com.project1.entity.Cart;

public interface CartService {
	CartResponseDTO addToCart(CartRequestDTO cartDTO);
	CartResponseDTO getCartByCustomerId(Long customerId);
	CartResponseDTO updateQuantity(Long customerId, String productId, int newQuantity);
	CartResponseDTO removeProductFromCart(Long customerId, String productId);
	void clearCart(Long customerId);
	//CartResponseDTO getCartDetails(Long customerId);
	

}
