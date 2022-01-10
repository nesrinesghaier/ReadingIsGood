package com.getir.readingisgood.service;

import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.repository.CustomerRepository;
import com.getir.readingisgood.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;

@Service
public class CustomerService {
    @Resource
    private CustomerRepository customerRepository;
    @Resource
    private OrderRepository orderRepository;

    public Page<Order> getCustomerOrders(String username, int pageNumber) {
        Pageable pageable =
                PageRequest.of(pageNumber, 3, Sort.by("orderDateTime"));
        return orderRepository.findAllByCustomerUsername(username, pageable);
    }

    public int getOrderCount(String username) {
        Customer customer = customerRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
        return customer.getOrders().size();
    }

}
