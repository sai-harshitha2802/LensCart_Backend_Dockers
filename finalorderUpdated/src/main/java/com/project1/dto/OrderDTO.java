package com.project1.dto;
 
import java.time.LocalDateTime;
import java.util.List;

import com.project1.entity.OrderStatus;
import com.project1.entity.PaymentStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
 
@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderDTO {
 
	private Long orderId;
 
	
	private Long customerId;
 
	@NotNull(message = "Address cannot be null")
	private String address;
 
//	@NotNull(message = "Pincode cannot be null")
//	private String pincode;
 
	private LocalDateTime orderDateTime;
 
	@NotNull(message = "Total amount cannot be null")
	private double totalAmount;
 
	@NotNull(message = "Payment status cannot be null")
	private PaymentStatus paymentStatus; // ✅ Use imported one
 
	@NotNull(message = "Order status cannot be null")
	private OrderStatus orderStatus;     // ✅ Use imported one

	private String razorpayOrderId;
 
	private List<OrderItemDTO> orderItems;
 
	// ✅ Full-arg constructor
	public OrderDTO(Long orderId, Long customerId, String address,LocalDateTime orderDateTime,
			double totalAmount, PaymentStatus paymentStatus, OrderStatus orderStatus, List<OrderItemDTO> orderItems) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.address = address;
		
		this.orderDateTime = orderDateTime;
		this.totalAmount = totalAmount;
		this.paymentStatus = paymentStatus;
		this.orderStatus = orderStatus;
		this.orderItems = orderItems;
	}
	

}