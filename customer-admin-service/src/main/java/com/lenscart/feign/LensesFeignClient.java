package com.lenscart.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lenscart.dto.LensesDTO;

@FeignClient(name = "Lenses")
public interface LensesFeignClient {
	

    @PostMapping("/add-lense")
    LensesDTO addLens(@RequestBody LensesDTO lensDTO);

    @PutMapping("/{lensId}")
    LensesDTO updateLens(@PathVariable String lensId, @RequestBody LensesDTO lensDTO);

    @DeleteMapping("/{lensId}")
    boolean deleteLens(@PathVariable String lensId);
}
