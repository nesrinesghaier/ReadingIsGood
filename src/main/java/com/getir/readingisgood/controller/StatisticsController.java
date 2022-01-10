package com.getir.readingisgood.controller;

import com.getir.readingisgood.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController()
@RequestMapping("/api/statistic")
@Slf4j
public class StatisticsController {

    @Resource
    private OrderService orderService;

    @GetMapping("")
    public ResponseEntity<?> getMonthlyStatistics(@RequestParam String email) {
        try {
            return ResponseEntity.ok(orderService.getOrderStatistics(email));
        } catch (UsernameNotFoundException e) {
            log.error("Customer with given email does not exists");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}