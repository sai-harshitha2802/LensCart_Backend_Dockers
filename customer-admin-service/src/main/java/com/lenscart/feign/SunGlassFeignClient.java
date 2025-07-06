package com.lenscart.feign;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.lenscart.dto.SunGlassDTO;

@FeignClient(name = "sunglassmodule")
public interface SunGlassFeignClient {

    @PostMapping("/add")
    SunGlassDTO addSunGlass(@RequestBody SunGlassDTO sunglassDTO);

    @PutMapping("/update/{sunglassId}")
    SunGlassDTO updateSunGlass(@PathVariable String sunglassId, @RequestBody SunGlassDTO sunglassDTO);

    @DeleteMapping("/{sunglassId}")
    boolean deleteSunGlass(@PathVariable String sunglassId);
}

