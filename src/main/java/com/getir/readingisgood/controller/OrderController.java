package com.getir.readingisgood.controller;

import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.exception.BookStockUnavailableException;
import com.getir.readingisgood.model.OrderDto;
import com.getir.readingisgood.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import javax.persistence.OptimisticLockException;
import java.time.LocalDate;
import java.util.List;

@RestController()
@RequestMapping("/api/order")
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("")
    public ResponseEntity<?> placeOrder(@RequestBody OrderDto orderDto) {
        try {
            Order order = orderService.addOrder(orderDto);
            log.info("Order placed successfully");
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (BookStockUnavailableException e) {
            log.error("The book stock with title {} is not available in stock anymore", e.getBookTitle());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (OptimisticLockException e) {
            log.error("The specified book is not available in stock anymore");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            log.error("Customer with email " + orderDto.getCustomerEmail() + " not found");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable long id) {
        try {
            log.info("Retrieving order with given id");
            return ResponseEntity.ok(orderService.getOrderById(id));
        } catch (EntityNotFoundException e) {
            log.error("Order with given id was not found");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Order>> getOrderBetweenDates(@RequestParam LocalDate startDate,
                                                            @RequestParam LocalDate endDate) {
        log.info("Retrieving order between {} and {} dates", startDate, endDate);
        return ResponseEntity.ok(orderService.getOrdersBetweenDates(startDate, endDate));
    }
}
