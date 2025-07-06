package com.capg.controller;

import com.capg.dto.OrderDTO;
import com.capg.dto.OrderStatusUpdateRequest;
import com.capg.dto.PaymentRequest;
import com.capg.dto.PaymentResponse;
import com.capg.dto.RazorpayVerificationRequest;
import com.capg.entity.Order;
import com.capg.exception.CustomerNotFoundException;
import com.capg.exception.OrderNotFoundException;
import com.capg.service.OrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place/{customerId}")
    public OrderDTO placeOrder(@PathVariable Long customerId,@RequestParam String address) {
        OrderDTO orderDto =orderService.placeOrder(customerId, address);
        return orderDto;
    }
    
    @GetMapping("/customer")
    
    public ResponseEntity<List<OrderDTO>> getCustomerOrders(@RequestParam Long customerId) {
        
        List<OrderDTO> orders = orderService.getCustomerOrders(customerId);
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/all")
    
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        
        List<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
 
//    @PutMapping("/make-payment/{orderId}")
//    
//    public ResponseEntity<PaymentResponse> makePayment(@PathVariable Long orderId, @RequestBody PaymentRequest paymentRequest) {
//        //logger.info("Processing payment for Order ID: {}", orderId);
//        PaymentResponse response = orderService.makePayment(orderId, paymentRequest.getAmount());
//        return ResponseEntity.ok(response);
//    }
    @PutMapping("/make-payment/{orderId}")
    public ResponseEntity<PaymentResponse> makePayment(
            @PathVariable Long orderId,
            @RequestBody PaymentRequest paymentRequest) {
        
        PaymentResponse response = orderService.makePayment(orderId, paymentRequest.getAmount());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify-payment")
    public ResponseEntity<String> verifyPayment(@RequestBody RazorpayVerificationRequest request) {
        String result = orderService.verifyPayment(request);
        return ResponseEntity.ok(result);
    }

    
    /**
     * Fetch Paid Orders for Admin
     */
    @GetMapping("/paid-orders")
    
    public ResponseEntity<List<OrderDTO>> getPaidOrders() {
        //logger.info("Fetching all paid orders for Admin");
        List<OrderDTO> orders = orderService.getPaidOrdersForAdmin();
        return ResponseEntity.ok(orders);
    }
 
    /**
     * Update order status in bulk (Admin)
     */
    @PutMapping("/update-order-status")
    
    public ResponseEntity<String> updateOrderStatusBulk(@RequestBody OrderStatusUpdateRequest request) {
        //logger.info("Updating statuses for orders: {}", request.getOrderIds());
        orderService.updateOrderStatusBulk(request.getOrderIds(), request.getStatus());
        return ResponseEntity.ok("Order statuses updated successfully.");
    }
 
    /**
     * Delete a pending order
     */
    @DeleteMapping("/delete/{orderId}")
    
    public ResponseEntity<String> deletePendingOrder(@PathVariable Long orderId) {
        //logger.info("Attempting to delete pending order with Order ID: {}", orderId);
        boolean isDeleted = orderService.deletePendingOrder(orderId);
 
        if (isDeleted) {
            return ResponseEntity.ok("Order successfully deleted.");
        } else {
            return ResponseEntity.status(400).body("Unable to delete. The order is either paid or in an invalid status.");
        }
    }
    
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId) {
        OrderDTO order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }
    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("Order cancelled and stock restored.");
    }
    
}
