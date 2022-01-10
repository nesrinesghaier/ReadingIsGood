package com.getir.readingisgood.service;

import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.model.EStatus;
import com.getir.readingisgood.repository.CustomerRepository;
import com.getir.readingisgood.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private OrderRepository orderRepository;

    @Test
    public void getCustomerOrdersTest() {
        Page<Order> page = new PageImpl<>(List.of(mockOrder()));
        when(customerRepository.findByEmail(any())).thenReturn(Optional.of(Customer.builder().build()));
        when(orderRepository.findAllByCustomerEmail(any(), any())).thenReturn(page);
        customerService.getCustomerOrders("test@test.com", 0);
        verify(orderRepository, times(1)).findAllByCustomerEmail(any(), any());
    }

    @Test
    public void getCustomerOrdersExceptionTest() {
        Page<Order> page = new PageImpl<>(List.of(mockOrder()));
        when(customerRepository.findByEmail(any())).thenThrow(new EntityNotFoundException("Customer with given email does not exist"));
        Assert.assertThrows(EntityNotFoundException.class, () -> customerService.getCustomerOrders("test@test.com", 0));
    }

    Order mockOrder() {
        return Order.builder()
                .id(1)
                .orderDateTime(LocalDateTime.now())
                .status(EStatus.PENDING)
                .customer(Customer.builder().email("test@test.com").build())
                .build();
    }
}