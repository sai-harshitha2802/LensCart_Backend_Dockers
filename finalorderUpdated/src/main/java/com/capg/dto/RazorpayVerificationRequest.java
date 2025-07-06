package com.capg.dto;

import java.time.LocalDateTime;

import com.capg.entity.OrderStatus;
import com.capg.entity.PaymentStatus;

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
