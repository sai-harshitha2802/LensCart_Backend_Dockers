package com.project1.feign;

import java.util.List;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.project1.dto.ProductDTO;
//@FeignClient(name = "framesmodule")
@FeignClient(name = "Frames")
public interface FramesClient {
	@GetMapping("/product-frames")
	List<ProductDTO> getFrames();
	@GetMapping("/{frameId}")
	ProductDTO getFrameById(@PathVariable String frameId);
	
	 @PutMapping("/reduce-stock/{frameId}/{quantity}")
	    void reduceStock(@PathVariable("frameId") String frameId,
	                     @PathVariable("quantity") int quantity);
	 
	 @PutMapping("/increase-stock/{frameId}/{quantity}")
	    void increaseStock(@PathVariable("frameId") String frameId,
	                     @PathVariable("quantity") int quantity);

}
