package com.omkaras.ff4j.controller;

import com.omkaras.ff4j.model.ToggleResponse;
import com.omkaras.ff4j.service.FF4JService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/ff4j/v1")
@RestController
public class FF4JController {

    private final FF4JService ff4JService;

    public FF4JController(FF4JService ff4JService){
        this.ff4JService = ff4JService;
    }

    @GetMapping("/toggles")
    public ToggleResponse getAllToggles(HttpServletRequest httpServletRequest){
        return ff4JService.getAllToggles(httpServletRequest);
    }
}
