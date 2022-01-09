package com.getir.readingisgood.controller;

import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.model.OrderDto;
import com.getir.readingisgood.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@RestController("order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping
    public void placeOrder(@RequestBody OrderDto orderDto) {
        orderService.addOrder(orderDto);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable long id) {
        return orderService.getOrder(id);
    }

    @GetMapping("")
    public List<Order> getOrderBetweenDates(@RequestParam LocalDate startDate,
                                            @RequestParam LocalDate endDate) {
        return orderService.getOrdersBetweenDates(startDate, endDate);
    }
}
