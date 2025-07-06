package com.lenscart.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.lenscart.dto.CartRequestDTO;
import com.lenscart.dto.CartResponseDTO;

//import io.swagger.v3.oas.annotations.parameters.RequestBody;

@FeignClient(name = "cart")
public interface CartFeignClient {

	@PostMapping("/cart/add")
    CartResponseDTO addToCart(@RequestBody CartRequestDTO cartRequestDTO);

    @GetMapping("/cart/{customerId}")
    CartResponseDTO getCartByCustomerId(@PathVariable Long customerId);
}
