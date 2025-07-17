package com.project1.feign;

import java.util.List;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.project1.dto.ProductDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

@FeignClient(name = "sunglassmodule")
public interface SunGlassClient {
	@GetMapping("/product-sunglass")
	List<ProductDTO> getSunGlass();
	@GetMapping("/{id}")
	ProductDTO getSunGlassById(@PathVariable String id);
	@PutMapping("/reduce-stock/{id}/{quantity}")
    void reduceStock(@PathVariable("id") String id,
                     @PathVariable("quantity") int quantity);
	
	@PutMapping("/increase-stock/{id}/{quantity}")
    void increaseStock(@PathVariable("id") String id,
                     @PathVariable("quantity") int quantity);

}
