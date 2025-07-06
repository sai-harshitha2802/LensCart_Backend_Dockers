package com.lenscart.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
	private Long orderId;
    private Long customerId;
    private double totalAmount;
    private String paymentStatus;
    private String message;
}