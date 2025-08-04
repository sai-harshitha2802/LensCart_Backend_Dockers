package com.project1.dto;
 
import lombok.*;
 
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
 
    
	private String productId;
    
    private int quantity;
    
    private double price;
    
    private String imageUrl;
}