package com.project1.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project1.dto.CartItemDTO;
import com.project1.dto.CartRequestDTO;
import com.project1.dto.CartResponseDTO;
import com.project1.entity.Cart;
import com.project1.entity.CartItem;
import com.project1.exception.CartNotFoundException;
import com.project1.exception.ProductNotFoundException;
import com.project1.exception.StockNotAvailableException;
import com.project1.feign.ProductFeignClient;
import com.project1.repository.CartRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Transactional
    @Override
    public CartResponseDTO addToCart(CartRequestDTO cartRequest) {
        // Get or create cart for the customer
        Cart cart = cartRepository.findByCustomerId(cartRequest.getCustomerId())
                .orElseGet(() -> new Cart(cartRequest.getCustomerId()));

        // Fetch product details using Feign client
        var product = productFeignClient.getProductById(cartRequest.getProductId());

        // Check if this product already exists in the current user's cart
        Optional<CartItem> existingItemOpt = cart.getCartItems().stream()
                .filter(item -> item.getProductId().equalsIgnoreCase(cartRequest.getProductId()))
                .findFirst();

        int newQuantity = cartRequest.getQuantity();

        // Update quantity if already present
        if (existingItemOpt.isPresent()) {
            CartItem item = existingItemOpt.get();
            item.setQuantity(item.getQuantity() + newQuantity);
            item.setPrice(product.getPrice()); // Update to latest price if needed
        } else {
            // Add new cart item
            CartItem newItem = new CartItem(
                    cartRequest.getProductId(),
                    newQuantity,
                    product.getPrice(),
                    product.getImageUrl(),
                    cart
            );
            cart.getCartItems().add(newItem);
        }

        // Recalculate total amount
        cart.calculateTotalAmount();

        // Save cart to repository
        cart = cartRepository.save(cart);

        return convertToDTO(cart);
    }

    @Override
    public CartResponseDTO getCartByCustomerId(Long customerId) {
        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for customer id: " + customerId));
        return convertToDTO(cart);
    }

    @Transactional
    @Override
    public CartResponseDTO updateQuantity(Long customerId, String productId, int newQuantity) {
    Cart cart = cartRepository.findByCustomerId(customerId)
            .orElseThrow(() -> new CartNotFoundException("Cart not found for customer id: " + customerId));

    CartItem item = cart.getCartItems().stream()
            .filter(ci -> ci.getProductId().equals(productId))
            .findFirst()
            .orElseThrow(() -> new ProductNotFoundException("Product not found in cart: " + productId));

    var product = productFeignClient.getProductById(productId);

    // ❌ REMOVE stock validation logic
    // int totalQuantityInAllCarts = getTotalQuantityInAllCarts(productId);
    // int requestedTotal = totalQuantityInAllCarts - item.getQuantity() + newQuantity;
    // if (requestedTotal > product.getQuantity()) {
    //     throw new StockNotAvailableException("Not enough stock for product " + productId);
    // }

    item.setQuantity(newQuantity);
    item.setPrice(product.getPrice());

    cart.calculateTotalAmount();
    cart = cartRepository.save(cart);

    return convertToDTO(cart);
}


    @Transactional
    @Override
    public CartResponseDTO removeProductFromCart(Long customerId, String productId) {
        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for customer id: " + customerId));

        boolean removed = cart.getCartItems().removeIf(item -> item.getProductId().equals(productId));

        if (!removed) {
            throw new ProductNotFoundException("Product not found in cart: " + productId);
        }

        cart.calculateTotalAmount();

        cart = cartRepository.save(cart);
        return convertToDTO(cart);
    }

    @Transactional
    @Override
    public void clearCart(Long customerId) {
        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for customer id: " + customerId));
        cart.getCartItems().clear();
        cart.setTotalAmount(0.0);
        cartRepository.save(cart);
    }

    private int getTotalQuantityInAllCarts(String productId) {
        List<Cart> allCarts = cartRepository.findAll();
        int total = 0;
        for (Cart cart : allCarts) {
            for (CartItem item : cart.getCartItems()) {
                if (item.getProductId().equals(productId)) {
                    total += item.getQuantity();
                }
            }
        }
        return total;
    }

    private CartResponseDTO convertToDTO(Cart cart) {
        CartResponseDTO dto = new CartResponseDTO();
        dto.setCartId(cart.getCartId());
        dto.setCustomerId(cart.getCustomerId());

        List<CartItemDTO> items = new ArrayList<>();
        for (CartItem item : cart.getCartItems()) {
            CartItemDTO itemDTO = new CartItemDTO();
            itemDTO.setProductId(item.getProductId());
            itemDTO.setQuantity(item.getQuantity());
            itemDTO.setPrice(item.getPrice());
            itemDTO.setImageUrl(item.getImageUrl());
            items.add(itemDTO);
        }
        dto.setCartItems(items);
        dto.setTotalAmount(cart.getTotalAmount());
        return dto;
    }
}
