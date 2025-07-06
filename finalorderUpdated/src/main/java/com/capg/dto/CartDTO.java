package com.capg.dto;
 
 
import java.util.List;
 
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
	private Long customerId;
    private List<CartItemDTO> cartItems;
    private double totalAmount; 
}