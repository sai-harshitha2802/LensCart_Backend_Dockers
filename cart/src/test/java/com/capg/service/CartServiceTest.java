package com.capg.service;
 
import com.capg.dto.CartRequestDTO;
import com.capg.dto.CartResponseDTO;
import com.capg.entity.Cart;
import com.capg.entity.CartItem;
import com.capg.exception.CartNotFoundException;
import com.capg.exception.ProductNotFoundException;
import com.capg.exception.StockNotAvailableException;
import com.capg.feign.ProductFeignClient;
import com.capg.repository.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
@ExtendWith(MockitoExtension.class)
public class CartServiceTest {
 
    @Mock
    private CartRepository cartRepository;
 
    @Mock
    private ProductFeignClient productFeignClient;
 
    @InjectMocks
    private CartServiceImpl cartService;
 
    private Cart cart;
    private CartRequestDTO cartRequest;
    private CartItem cartItem;
 
    @BeforeEach
    public void setUp() {
        cart = new Cart(1L);
        cartItem = new CartItem("P001", 2, 100.0, "imageUrl", cart);
        cart.getCartItems().add(cartItem);
 
        cartRequest = new CartRequestDTO();
        cartRequest.setCustomerId(1L);
        cartRequest.setProductId("P001");
        cartRequest.setQuantity(3);
    }
    @Test
    public void testGetCartByCustomerId_InvalidCustomer() {
        when(cartRepository.findByCustomerId(999L)).thenReturn(Optional.empty());
 
        assertThrows(CartNotFoundException.class, () -> cartService.getCartByCustomerId(999L));
    }
 
    @Test
    public void testUpdateQuantity_ProductNotInCart() {
        when(cartRepository.findByCustomerId(1L)).thenReturn(Optional.of(cart));
 
        assertThrows(ProductNotFoundException.class, () -> cartService.updateQuantity(1L, "P999", 5));
    }
 
   
    @Test
    public void testRemoveProductFromCart_ProductNotInCart() {
        when(cartRepository.findByCustomerId(1L)).thenReturn(Optional.of(cart));
 
        assertThrows(ProductNotFoundException.class, () -> cartService.removeProductFromCart(1L, "P999"));
    }
 
    @Test
    public void testClearCart_ValidCustomer() {
        when(cartRepository.findByCustomerId(1L)).thenReturn(Optional.of(cart));
 
        cartService.clearCart(1L);
 
        verify(cartRepository, times(1)).delete(cart);
    }
 
    @Test
    public void testClearCart_InvalidCustomer() {
        when(cartRepository.findByCustomerId(999L)).thenReturn(Optional.empty());
 
        assertThrows(CartNotFoundException.class, () -> cartService.clearCart(999L));
    }
}