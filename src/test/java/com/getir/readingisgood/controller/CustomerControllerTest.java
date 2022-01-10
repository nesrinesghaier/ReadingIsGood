package com.getir.readingisgood.controller;

import com.getir.readingisgood.exception.UserAlreadyExistException;
import com.getir.readingisgood.model.LoginRequest;
import com.getir.readingisgood.model.SignUpRequest;
import com.getir.readingisgood.service.AuthService;
import com.getir.readingisgood.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {
    @InjectMocks
    private CustomerController customerController;
    @Mock
    private AuthService authService;
    @Mock
    private CustomerService customerService;

    @Test
    public void signInTest() {
        LoginRequest loginRequest = LoginRequest.builder().email("test@test.com").password("test123").build();
        ResponseEntity<?> response = customerController.signIn(loginRequest);
        verify(authService, times(1)).signIn(any());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void signUpTest() {
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .email("test@test.com")
                .password("test123")
                .firstName("test")
                .lastName("test")
                .build();
        customerController.signUp(signUpRequest);
        verify(authService, times(1)).registerCustomer(any());
    }

    @Test
    public void signUpBadRequestTest() {
        when(authService.registerCustomer(any()))
                .thenThrow(new UserAlreadyExistException("User with this email already exists"));
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .email("test@test.com")
                .password("test123")
                .firstName("test")
                .lastName("test")
                .build();
        ResponseEntity<String> response = customerController.signUp(signUpRequest);
        verify(authService, times(0)).signIn(any());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void getCustomerOrdersTestWithEmailNotFound() {
        when(customerService.getCustomerOrders(anyString(), anyInt())).thenThrow(new EntityNotFoundException());
        ResponseEntity<?> response = customerController.getCustomerOrders("test1@test.com", 0);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void getCustomerOrdersTest() {
        when(customerService.getCustomerOrders(anyString(), anyInt())).thenReturn(new PageImpl<>(new ArrayList<>()));
        ResponseEntity<?> customerOrders = customerController.getCustomerOrders("test@test.com", 0);
        assertEquals(HttpStatus.OK, customerOrders.getStatusCode());
        verify(customerService, times(1)).getCustomerOrders(anyString(), anyInt());
    }
}