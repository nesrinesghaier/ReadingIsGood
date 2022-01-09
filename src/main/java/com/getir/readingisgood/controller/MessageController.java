package com.getir.readingisgood.controller;

import com.getir.readingisgood.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MessageController {

    @Resource
    private UserService userService;

    @GetMapping("/test")
    public String getMessage() {
        return "Hey there";
    }


}
