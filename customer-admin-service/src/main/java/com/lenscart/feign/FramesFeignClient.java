package com.lenscart.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lenscart.dto.FramesDTO;

@FeignClient(name = "Frames")
public interface FramesFeignClient {

    @PostMapping("/add")
    FramesDTO addFrame(@RequestBody FramesDTO frameDTO);

    @PutMapping("/{frameId}")
    FramesDTO updateFrame(@PathVariable String frameId, @RequestBody FramesDTO frameDTO);

    @DeleteMapping("/{frameId}")
    boolean deleteFrame(@PathVariable String frameId);
}

