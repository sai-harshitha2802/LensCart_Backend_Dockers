package com.project1.feign;

import java.util.List;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.project1.dto.ProductDTO;

import io.swagger.v3.oas.annotations.Operation;
@FeignClient(name = "glassmodule")
public interface GlassesClient {
	@GetMapping("/product-glasses")
	List<ProductDTO> getGlasses();
	@GetMapping("/{glassId}")
	
	ProductDTO getGlassById(@PathVariable String glassId);
	
	@PutMapping("/reduce-stock/{glassId}/{quantity}")
    void reduceStock(@PathVariable("glassId") String glassId,
                     @PathVariable("quantity") int quantity);

	
	@PutMapping("/increase-stock/{glassId}/{quantity}")
    void increaseStock(@PathVariable("glassId") String glassId,
                     @PathVariable("quantity") int quantity);

}
