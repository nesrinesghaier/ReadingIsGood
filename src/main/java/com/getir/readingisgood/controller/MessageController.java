package com.getir.readingisgood.controller;

import com.getir.readingisgood.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MessageController {

    @Resource
    private CustomerService customerService;

    @GetMapping("/test")
    public String getMessage() {

        customerService.registerCustomer();
        return "Hey there";
    }


}
