package com.lenscart.service;
 
import com.lenscart.dto.CartRequestDTO;
import com.lenscart.dto.CartResponseDTO;
import com.lenscart.dto.ProductDTO;
import com.lenscart.entity.Customer;
import com.lenscart.exception.InvalidCredentialsException;
import com.lenscart.exception.UserNotFoundException;
import com.lenscart.feign.CartFeignClient;
import com.lenscart.feign.ProductServiceClient;
import com.lenscart.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
 
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
class CustomerServiceImplTest {
 
    @Mock private CustomerRepository customerRepository;
    @Mock private ProductServiceClient productServiceClient;
    @Mock private CartFeignClient cartServiceClient;
    @Mock private PasswordEncoder passwordEncoder;
 
    private CustomerServiceImpl customerService;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(
            customerRepository,
            productServiceClient,
            cartServiceClient,
            passwordEncoder
        );
    }
 
    @Test
    void testLoginCustomer_Success() {
        Customer customer = new Customer();
        customer.setEmail("user@example.com");
        customer.setPassword("encodedPass");
 
        when(customerRepository.findByEmail("user@example.com")).thenReturn(Optional.of(customer));
        when(passwordEncoder.matches("rawPass", "encodedPass")).thenReturn(true);
 
        boolean result = customerService.loginCustomer("user@example.com", "rawPass");
 
        assertTrue(result);
    }
 
    @Test
    void testLoginCustomer_UserNotFound() {
        when(customerRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());
 
        assertThrows(UserNotFoundException.class, () ->
                customerService.loginCustomer("notfound@example.com", "pass"));
    }
 
    @Test
    void testViewAllProducts() {
        ProductDTO product1 = new ProductDTO();
        ProductDTO product2 = new ProductDTO();
        List<ProductDTO> products = Arrays.asList(product1, product2);
 
        when(productServiceClient.getAllProducts()).thenReturn(products);
 
        List<ProductDTO> result = customerService.viewAllProducts();
 
        assertEquals(2, result.size());
        verify(productServiceClient).getAllProducts();
    }
 
    @Test
    void testAddToCart() {
        CartRequestDTO request = new CartRequestDTO();
        CartResponseDTO response = new CartResponseDTO();
 
        when(cartServiceClient.addToCart(request)).thenReturn(response);
 
        CartResponseDTO result = customerService.addToCart(request);
 
        assertEquals(response, result);
        verify(cartServiceClient).addToCart(request);
    }
}
 