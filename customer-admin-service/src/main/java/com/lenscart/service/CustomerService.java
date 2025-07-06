package com.lenscart.service;

import java.util.List;

import com.lenscart.dto.CartRequestDTO;
import com.lenscart.dto.CartResponseDTO;
import com.lenscart.dto.ProductDTO;
import com.lenscart.entity.Customer;

public interface CustomerService {
    boolean loginCustomer(String email, String password);
    List<ProductDTO> viewAllProducts();
    CartResponseDTO addToCart(CartRequestDTO cartRequestDTO);
    CartResponseDTO getCartByCustomerId(Long customerId);
    public Customer getCustomerById(Long id) ;
}
