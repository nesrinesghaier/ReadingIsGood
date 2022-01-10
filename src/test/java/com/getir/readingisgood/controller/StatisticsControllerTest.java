package com.getir.readingisgood.controller;

import com.getir.readingisgood.model.StatisticsDto;
import com.getir.readingisgood.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsControllerTest {
    @InjectMocks
    private StatisticsController statisticsController;

    @Mock
    private OrderService orderService;

    @Test
    public void getMonthlyStatistics() {
        when(orderService.getOrderStatistics(any())).thenReturn(mockListOfStatisticsDto());
        ResponseEntity<?> response = statisticsController.getMonthlyStatistics("test@test.com");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    List<StatisticsDto> mockListOfStatisticsDto() {
        return List.of(StatisticsDto.builder()
                .month("January")
                .orderCount(5)
                .purchasedOrdersAmount(200.3)
                .purchasedBooksCount(6)
                .build());
    }
}