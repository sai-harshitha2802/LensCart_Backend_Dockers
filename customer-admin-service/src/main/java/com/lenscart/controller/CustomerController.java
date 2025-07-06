package com.lenscart.controller;

import com.lenscart.dto.CartRequestDTO;

import com.lenscart.dto.CartResponseDTO;
import com.lenscart.dto.OrderDTO;
import com.lenscart.dto.ProductDTO;
import com.lenscart.entity.Customer;
import com.lenscart.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer Controller", description = "Handles customer operations")
public class CustomerController {

    private final CustomerService customerService;
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    



    @GetMapping("/products/all")
    @Operation(summary = "View all products (Accessible without login)")
    public ResponseEntity<Object> viewAllProducts() {
        logger.info("Fetching all products for customer");
        List<ProductDTO> products = customerService.viewAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/cart/add")
    @Operation(summary = "Add a product to the cart")
    public ResponseEntity<Object> addToCart(@RequestBody CartRequestDTO cartRequestDTO) {
        try {
            CartResponseDTO addedCart = customerService.addToCart(cartRequestDTO);
            return ResponseEntity.ok(addedCart);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding item to cart: " + e.getMessage());
        }
    }

    @GetMapping("/cart/{customerId}")
    @Operation(summary = "Get cart details for a customer")
    public ResponseEntity<Object> getCartByCustomerId(@PathVariable Long customerId) {
        try {
            CartResponseDTO cart = customerService.getCartByCustomerId(customerId);
            return ResponseEntity.ok(cart);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching cart: " + e.getMessage());
        }
    }
    
	/*
	 * @PostMapping("/place-order")
	 * 
	 * @Operation(summary = "Place an order for a customer") public
	 * ResponseEntity<?> placeOrder(
	 * 
	 * @RequestParam Long customerId,
	 * 
	 * @RequestParam String address) { try { OrderDTO order =
	 * customerService.placeOrder(customerId, address); // You call OrderService
	 * internally return ResponseEntity.ok(order); } catch (Exception e) { return
	 * ResponseEntity.status(500).body("Failed to place order: " + e.getMessage());
	 * } }
	 * 
	 * @PutMapping("/make-payment/{orderId}")
	 * 
	 * @Operation(summary = "Make payment for a specific order") public
	 * ResponseEntity<?> makePayment(
	 * 
	 * @PathVariable Long orderId,
	 * 
	 * @RequestBody PaymentRequest paymentRequest) { try { PaymentResponse response
	 * = customerService.makePayment(orderId, paymentRequest); return
	 * ResponseEntity.ok(response); } catch (Exception e) { return
	 * ResponseEntity.status(500).body("Payment failed: " + e.getMessage()); } }
	 * 
	 */
}
