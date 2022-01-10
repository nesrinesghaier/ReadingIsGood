package com.getir.readingisgood.controller;

import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.model.OrderDto;
import com.getir.readingisgood.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@RestController()
@RequestMapping("/api/order")
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("")
    public ResponseEntity<String> placeOrder(@RequestBody OrderDto orderDto) {
        orderService.addOrder(orderDto);
        log.info("Order placed successfully");
        return new ResponseEntity<>("Order placed successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("")
    public ResponseEntity<List<Order>> getOrderBetweenDates(@RequestParam LocalDate startDate,
                                                            @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(orderService.getOrdersBetweenDates(startDate, endDate));
    }
}
