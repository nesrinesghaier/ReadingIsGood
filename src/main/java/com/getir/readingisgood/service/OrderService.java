package com.getir.readingisgood.service;

import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.entity.OrderDetail;
import com.getir.readingisgood.model.OrderDto;
import com.getir.readingisgood.model.StatisticsDto;
import com.getir.readingisgood.repository.CustomerRepository;
import com.getir.readingisgood.repository.OrderRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private CustomerRepository customerRepository;
    @Resource
    private OrderDetailService orderDetailService;

    public Order addOrder(OrderDto orderDto) {
        Customer customer = customerRepository.findByEmail(orderDto.getCustomerEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Customer with email " + orderDto.getCustomerEmail() + " not found"));
        Order order = Order.builder()
                .orderDateTime(LocalDateTime.now())
                .status(orderDto.getStatus())
                .customer(customer)
                .build();
        addOrderDetailsDtoToOrder(orderDto, order);
        return orderRepository.save(order);
    }

    public Order getOrderById(long orderId) {
        return orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
    }

    public List<Order> getOrdersBetweenDates(LocalDate startDate, LocalDate endDate) {
        return orderRepository.findAllByOrderDateTimeBetween(startDate.atStartOfDay(), endDate.atTime(23, 59));
    }

    public List<StatisticsDto> getOrderStatistics(String email) {
        List<StatisticsDto> monthlyOrdersCount = orderRepository.getOrdersCountByMonth(email);
        List<StatisticsDto> statisticsDtoList = orderRepository.getPurchasedBooksCountByMonth(email);
        statisticsDtoList.forEach(s -> s.setOrderCount(monthlyOrdersCount.stream()
                .filter(t -> t.getMonth().trim().equals(s.getMonth().trim()))
                .findFirst().orElse(StatisticsDto.builder().build()).getOrderCount()));
        return statisticsDtoList;
    }

    private Order addOrderDetailsDtoToOrder(OrderDto orderDto, Order order) {

        List<OrderDetail> orderDetails = orderDto.getOrderDetails().stream()
                .map(detailDto -> orderDetailService.buildOrderDetails(detailDto, order))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return Order.builder()
                .orderDateTime(orderDto.getOrderDateTime() != null ? orderDto.getOrderDateTime() : LocalDateTime.now())
                .status(orderDto.getStatus())
                .orderDetails(orderDetails)
                .build();
    }


}
