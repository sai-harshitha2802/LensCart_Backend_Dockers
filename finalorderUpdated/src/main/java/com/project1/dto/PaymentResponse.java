package com.project1.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter

@NoArgsConstructor

public class PaymentResponse {
    private Long backendOrderId;
    private Long customerId;
    private double amount;
    private String status;
    private String message;
    private String razorpayOrderId;
    
 // ✅ Constructor with 6 parameters (matches makePayment())
    public PaymentResponse(Long backendOrderId, Long customerId, double amount,
                           String status, String message, String razorpayOrderId) {
        this.backendOrderId = backendOrderId;
        this.customerId = customerId;
        this.amount = amount;
        this.status = status;
        this.message = message;
        this.razorpayOrderId = razorpayOrderId;
    }

    // ✅ Also add this if you're using only 5 parameters elsewhere
    public PaymentResponse(Long backendOrderId, Long customerId, double amount,
                           String status, String message) {
        this.backendOrderId = backendOrderId;
        this.customerId = customerId;
        this.amount = amount;
        this.status = status;
        this.message = message;
    }


    // Constructors, getters, and setters
}
