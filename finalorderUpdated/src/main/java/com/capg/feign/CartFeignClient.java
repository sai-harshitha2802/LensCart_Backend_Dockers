//package com.capg.feign;
//
//import org.springframework.cloud.openfeign.FeignClient;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import com.capg.dto.CartRequestDTO;
//
//import java.util.List;
//
//@FeignClient(name = "finalcart")  // ✅ Update with your Cart Service URL
//public interface CartFeignClient {
//    @GetMapping("/cart/{customerId}")
//    CartRequestDTO getCartByCustomer(@PathVariable Long customerId);
//}



package com.capg.feign;
 
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

 
import com.capg.dto.CartDTO;
 
@FeignClient(name = "cart", url = "http://localhost:8087/cart")

public interface CartFeignClient {
 
	@GetMapping("/{customerId}")

    CartDTO getCart(@PathVariable Long customerId);  // Corrected parameter name

}

 