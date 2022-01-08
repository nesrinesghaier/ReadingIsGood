package com.getir.readingisgood.service;

import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CustomerService {
    @Resource
    private CustomerRepository customerRepository;

    public void registerCustomer() {
        Customer customer = Customer.builder().email("test")
                .firstName("test").lastName("test").address("test").build();
        customerRepository.save(customer);
    }
}
