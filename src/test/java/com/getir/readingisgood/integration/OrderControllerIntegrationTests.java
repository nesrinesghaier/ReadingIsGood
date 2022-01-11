package com.getir.readingisgood.integration;

import com.getir.readingisgood.controller.OrderController;
import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.model.EStatus;
import com.getir.readingisgood.model.OrderDetailDto;
import com.getir.readingisgood.model.OrderDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class OrderControllerIntegrationTests {

    @Resource
    OrderController orderController;

    @Test
    void testPlaceOrder() {
        OrderDto orderDto = mockOrderDto();
        ResponseEntity<?> response = orderController.placeOrder(orderDto);
        assertThat(response).hasFieldOrPropertyWithValue("status", HttpStatus.CREATED);
        long orderId = Objects.requireNonNull((Order) response.getBody()).getId();
        assertThat(orderId).isPositive();
    }

    @Test
    void testPlaceOrderSimultaneously() throws InterruptedException {
        OrderDto orderDto = mockOrderDto1();
        final ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> orderController.placeOrder(orderDto));
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
//        assertThrows(OptimisticLockException.class, () -> executor.execute(() -> orderController.placeOrder(orderDto)));
    }

    @Test
    void testGetOrderById() {
        ResponseEntity<?> response = orderController.getOrderById(1);
        assertThat(response).hasFieldOrPropertyWithValue("status", HttpStatus.OK);
        long orderId = Objects.requireNonNull((Order) response.getBody()).getId();
        assertThat(orderId).isEqualTo(1);
    }

    @Test
    void testGetOrderBetweenDates() {
        ResponseEntity<List<Order>> response = orderController.getOrderBetweenDates(LocalDate.of(2020, 12, 1),
                LocalDate.of(2021, 1, 1));
        assertThat(response).hasFieldOrPropertyWithValue("status", HttpStatus.OK);
        long size = Objects.requireNonNull(response.getBody()).size();
        assertThat(size).isEqualTo(2);
    }

    OrderDto mockOrderDto() {
        return OrderDto.builder()
                .orderDateTime(LocalDateTime.now())
                .orderDetails(List.of(OrderDetailDto
                        .builder()
                        .price(25.2)
                        .quantity(1)
                        .bookId(1)
                        .build()))
                .customerEmail("test11@test.com")
                .build();
    }

    OrderDto mockOrderDto1() {
        return OrderDto.builder()
                .orderDateTime(LocalDateTime.now())
                .orderDetails(List.of(OrderDetailDto
                        .builder()
                        .price(25.2)
                        .quantity(1)
                        .bookId(4)
                        .build()))
                .customerEmail("test@test.com")
                .build();
    }
}
