package com.lenscart.feign;

import com.lenscart.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductServiceClient {

    @GetMapping("/products/all")
    List<ProductDTO> getAllProducts();
    
    @GetMapping("/products/{id}")
    ProductDTO getProductById(@PathVariable String id); 
    	


    @PostMapping
    ProductDTO addProduct(@RequestBody ProductDTO product);

    @PutMapping("/{id}")
    ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO product);

    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Long id);
}
