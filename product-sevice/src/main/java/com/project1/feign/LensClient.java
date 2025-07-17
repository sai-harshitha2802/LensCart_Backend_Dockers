package com.project1.feign;

import java.util.List;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project1.dto.ProductDTO;

//@FeignClient(name = "Springproj")
@FeignClient(name = "Lenses")
public interface LensClient {
	@GetMapping("/product-lenses")
    List<ProductDTO> getLenses();
	@GetMapping("/byId")
	ProductDTO getLensById(@RequestParam String lensId);
	@PutMapping("/reduce-stock/{lensId}/{quantity}")
    void reduceStock(@PathVariable("lensId") String lensId,
                     @PathVariable("quantity") int quantity);
	
	@PutMapping("/increase-stock/{lensId}/{quantity}")
    void increaseStock(@PathVariable("lensId") String lensId,
                     @PathVariable("quantity") int quantity);

}

