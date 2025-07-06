package com.lenscart.dto;


import lombok.*;
 
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
 
    private String productId;
    
    private int quantity;
    
    private double price;
    
}
