package com.project1.dto;

import java.time.LocalDateTime;

import com.project1.entity.OrderStatus;
import com.project1.entity.PaymentStatus;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RazorpayVerificationRequest {
	private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;

}
