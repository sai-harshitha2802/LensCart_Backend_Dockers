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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ProductServiceClient productServiceClient;
    private final CartFeignClient cartServiceClient;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    public CustomerServiceImpl(CustomerRepository customerRepository,
                                ProductServiceClient productServiceClient,
                                CartFeignClient cartServiceClient,
                                PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.productServiceClient = productServiceClient;
        this.cartServiceClient = cartServiceClient;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean loginCustomer(String email, String password) {
        logger.info("Customer login attempt: {}", email);
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);
        if (customerOptional.isEmpty()) {
            logger.error("Customer not found: {}", email);
            throw new UserNotFoundException("Customer not found with email: " + email);
        }

        Customer customer = customerOptional.get();
        if (!passwordEncoder.matches(password, customer.getPassword())) {
            logger.error("Invalid credentials for email: {}", email);
            throw new InvalidCredentialsException("Invalid credentials");
        }

        logger.info("Customer login successful: {}", email);
        return true;
    }

    @Override
    public List<ProductDTO> viewAllProducts() {
        logger.info("Fetching all products");
        return productServiceClient.getAllProducts();
    }

    @Override
    public CartResponseDTO addToCart(CartRequestDTO cartRequestDTO) {
        logger.info("Adding item to cart for customerId: {}", cartRequestDTO.getCustomerId());
        return cartServiceClient.addToCart(cartRequestDTO);
    }

    @Override
    public CartResponseDTO getCartByCustomerId(Long customerId) {
        logger.info("Fetching cart for customerId: {}", customerId);
        return cartServiceClient.getCartByCustomerId(customerId);
    }

	@Override
	public Customer getCustomerById(Long id) {
		// TODO Auto-generated method stub
		Customer customerdetails = customerRepository.findById(id).orElseThrow(()->new RuntimeException("Not Found Customer"));
		return customerdetails;
	}
    
    



}
