package com.getir.readingisgood.controller;

import com.getir.readingisgood.model.StatisticsDto;
import com.getir.readingisgood.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController()
@RequestMapping("/api/statistic")
public class StatisticsController {

    @Resource
    private OrderService orderService;

    @GetMapping("")
    public List<StatisticsDto> getMonthlyStatistics(@RequestParam String email) {
        return orderService.getOrderCount(email);
    }

}