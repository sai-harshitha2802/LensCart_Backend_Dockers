package com.project1.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project1.dto.ProductResponse;

@FeignClient(name = "product-service")
public interface ProductFeignClient {
    @GetMapping("/products/{id}")
    ProductResponse getProductById(@PathVariable("id") String productId);
}
