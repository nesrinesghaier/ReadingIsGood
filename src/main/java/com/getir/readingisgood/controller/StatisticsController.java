package com.getir.readingisgood.controller;

import com.getir.readingisgood.model.StatisticsDto;
import com.getir.readingisgood.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController()
@RequestMapping("/api/statistic")
public class StatisticsController {

    @Resource
    private OrderService orderService;

    @GetMapping("")
    public ResponseEntity<List<StatisticsDto>> getMonthlyStatistics(@RequestParam String email) {
        return ResponseEntity.ok(orderService.getOrderStatistics(email));
    }

}