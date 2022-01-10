package com.getir.readingisgood.controller;

import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.model.EStatus;
import com.getir.readingisgood.model.OrderDetailDto;
import com.getir.readingisgood.model.OrderDto;
import com.getir.readingisgood.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    @InjectMocks
    private OrderController OrderController;

    @Mock
    private OrderService OrderService;

    @Test
    public void addOrder() {
        OrderDto OrderDto = mockOrderDto();
        ResponseEntity<String> response = OrderController.placeOrder(OrderDto);
        verify(OrderService, times(1)).addOrder(any());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void getOrderById() {
        ResponseEntity<Order> response = OrderController.getOrderById(1);
        verify(OrderService, times(1)).getOrderById(anyLong());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getOrderBetweenDates() {
        ResponseEntity<List<Order>> response = OrderController
                .getOrderBetweenDates(LocalDate.now(), LocalDate.now().plusMonths(1));
        verify(OrderService, times(1)).getOrdersBetweenDates(any(), any());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    OrderDto mockOrderDto() {
        return OrderDto.builder()
                .orderDateTime(LocalDateTime.now())
                .status(EStatus.PENDING)
                .orderDetails(List.of(OrderDetailDto
                        .builder()
                        .price(25.2)
                        .quantity(2)
                        .build()))
                .build();
    }
}