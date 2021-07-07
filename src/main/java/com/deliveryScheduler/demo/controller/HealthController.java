package com.deliveryScheduler.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "health")
public class HealthController {

    @GetMapping("")
    public String healthCheck() {
        return "success";
    }

}
