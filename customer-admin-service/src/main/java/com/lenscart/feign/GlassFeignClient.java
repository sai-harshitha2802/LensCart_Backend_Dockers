package com.lenscart.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.lenscart.dto.GlassDTO;

@FeignClient(name = "glassmodule")
public interface GlassFeignClient {

    @PostMapping("/add-glasses")
    GlassDTO addGlass(@RequestBody GlassDTO glassDTO);

    @PutMapping("/{glassId}")
    GlassDTO updateGlass(@PathVariable String glassId, @RequestBody GlassDTO glassDTO);

    @DeleteMapping("/{glassId}")
    boolean deleteGlass(@PathVariable String glassId);
}

