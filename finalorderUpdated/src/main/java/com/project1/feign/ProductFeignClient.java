package com.project1.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project1.dto.ProductDTO;

// Replace 'product-service' with the actual service name registered in Eureka
@FeignClient(name = "product-service")
public interface ProductFeignClient {

    // Endpoint to fetch a product by its ID
    @GetMapping("/products/{id}")
    ProductDTO getProductById(@PathVariable("id") String productId);
    
    @PutMapping("/products/reduce-stock/{id}")
    void reduceStock(@PathVariable("id") String productId, @RequestParam("quantity") int quantity);
    
    @PutMapping("/products/increase-stock/{id}")
    void increaseStock(@PathVariable("id") String productId, @RequestParam("quantity") int quantity);
}
