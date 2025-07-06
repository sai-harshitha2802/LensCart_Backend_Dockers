package com.capg.service;

import java.util.List;
import java.util.Optional;

import com.capg.dto.CartRequestDTO;
import com.capg.dto.CartResponseDTO;
import com.capg.entity.Cart;

public interface CartService {
	CartResponseDTO addToCart(CartRequestDTO cartDTO);
	CartResponseDTO getCartByCustomerId(Long customerId);
	CartResponseDTO updateQuantity(Long customerId, String productId, int newQuantity);
	CartResponseDTO removeProductFromCart(Long customerId, String productId);
	void clearCart(Long customerId);
	//CartResponseDTO getCartDetails(Long customerId);
	

}
