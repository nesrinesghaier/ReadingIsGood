package com.getir.readingisgood.service;

import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.exception.UserNotFountException;
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

    public Page<Order> getCustomerOrders(String email, int pageNumber) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFountException("Customer with " + email + " email does not exist"));

        Pageable pageable = PageRequest.of(pageNumber, 3, Sort.by("orderDateTime"));
        return orderRepository.findAllByCustomerEmail(customer.getEmail(), pageable);
    }

    public int getOrderCount(String email) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
        return customer.getOrders().size();
    }

}
