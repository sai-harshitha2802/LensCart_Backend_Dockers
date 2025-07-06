package com.lenscart.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lenscart.dto.OrderDTO;

@FeignClient(name = "finalorderUpdated")
public interface OrderServiceClient {

	@PutMapping("/order/update-status/{orderId}")
	String updateOrder(@PathVariable Long orderId, @RequestParam String status);

	@GetMapping("/order/all")

	List<OrderDTO> getAllOrders();

	/*
	 * @PostMapping("/place/{customerId}") OrderDTO
	 * placeOrder(@PathVariable("customerId") Long
	 * customerId, @RequestParam("address") String address);
	 * 
	 * @PutMapping("/make-payment/{orderId}") PaymentResponse
	 * makePayment(@PathVariable("orderId") Long orderId, @RequestBody
	 * PaymentRequest request);
	 * 
	 * @GetMapping("/customer") List<OrderDTO>
	 * getCustomerOrders(@RequestParam("customerId") Long customerId);
	 */
}
