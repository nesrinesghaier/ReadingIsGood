package com.getir.readingisgood.service;

import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.entity.OrderDetail;
import com.getir.readingisgood.model.EStatus;
import com.getir.readingisgood.model.OrderDetailDto;
import com.getir.readingisgood.model.OrderDto;
import com.getir.readingisgood.repository.CustomerRepository;
import com.getir.readingisgood.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderDetailService orderDetailService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void addOrder() {
        OrderDto orderDto = mockOrderDto();
        when(customerRepository.findByEmail(any())).thenReturn(Optional.of(Customer.builder().email("test@test.com").build()));
        when(orderDetailService.buildOrderDetails(any(), any())).thenReturn(OrderDetail.builder().build());
        orderService.addOrder(orderDto);
        verify(orderRepository, times(1)).save(any());
    }

    @Test
    public void getOrderById() {
        when(orderRepository.findById(any())).thenReturn(Optional.of(Order.builder().build()));
        Order order = orderService.getOrderById(1);
        verify(orderRepository, times(1)).findById(any());
        assertNotNull(order);
    }

    @Test
    public void getOrdersBetweenDates() {
        when(orderRepository.findAllByOrderDateTimeBetween(any(), any()))
                .thenReturn(List.of(Order.builder().build()));
        List<Order> ordersBetweenDates = orderService.getOrdersBetweenDates(LocalDate.now(), LocalDate.now().plusMonths(1));
        verify(orderRepository, times(1)).findAllByOrderDateTimeBetween(any(), any());
        assertEquals(1, ordersBetweenDates.size());
    }

    OrderDto mockOrderDto() {
        return OrderDto.builder()
                .orderDateTime(LocalDateTime.now())
                .orderDetails(List.of(OrderDetailDto
                        .builder()
                        .price(25.2)
                        .quantity(2)
                        .build()))
                .customerEmail("test@test.com")
                .build();
    }
}