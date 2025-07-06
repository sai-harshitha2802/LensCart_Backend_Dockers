package com.capg.feign;

import com.capg.dto.ProductResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductFeignClient {
    @GetMapping("/products/{id}")
    ProductResponse getProductById(@PathVariable("id") String productId);
}
