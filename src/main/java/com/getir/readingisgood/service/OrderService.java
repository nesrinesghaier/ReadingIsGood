package com.getir.readingisgood.service;

import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.entity.OrderDetail;
import com.getir.readingisgood.model.OrderDto;
import com.getir.readingisgood.model.StatisticsDto;
import com.getir.readingisgood.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private OrderDetailService orderDetailService;

    public void addOrder(OrderDto orderDto) {
        Order order = mapDtoToOrder(orderDto);
        orderRepository.save(order);
    }

    public Order getOrder(long orderId) {
        return orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
    }

    public List<Order> getOrdersBetweenDates(LocalDate startDate, LocalDate endDate) {
        return orderRepository.findAllByOrderDateTimeBetween(startDate.atStartOfDay(), endDate.atTime(11, 59));
    }

    public List<StatisticsDto> getOrderCount(String email) {
        List<StatisticsDto> monthlyOrdersCount = orderRepository.getOrdersCountByMonth(email);
        List<StatisticsDto> statisticsDtoList = orderRepository.getPurchasedBooksCountByMonth(email);
        statisticsDtoList.forEach(s -> s.setOrderCount(monthlyOrdersCount.stream()
                .filter(t -> t.getMonth().trim().equals(s.getMonth().trim()))
                .findFirst().orElse(StatisticsDto.builder().build()).getOrderCount()));
        return statisticsDtoList;
    }

    private Order mapDtoToOrder(OrderDto orderDto) {
        List<OrderDetail> orderDetails = orderDto.getOrderDetails().stream()
                .map(o -> orderDetailService.buildOrderDetails(o))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return Order.builder()
                .orderDateTime(orderDto.getOrderDateTime() != null ? orderDto.getOrderDateTime() : LocalDateTime.now())
                .status(orderDto.getStatus())
                .orderDetails(orderDetails)
                .build();
    }
}
