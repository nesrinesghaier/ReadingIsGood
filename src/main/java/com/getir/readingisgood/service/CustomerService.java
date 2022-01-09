package com.getir.readingisgood.service;

import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CustomerService {
    @Resource
    private CustomerRepository customerRepository;

    public List<Order> getCustomerOrders(String username) {
        Customer customer = customerRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
        return customer.getOrders();
    }
}
