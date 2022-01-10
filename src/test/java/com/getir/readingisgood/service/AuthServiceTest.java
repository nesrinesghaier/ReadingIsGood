package com.getir.readingisgood.service;

import com.getir.readingisgood.model.LoginRequest;
import com.getir.readingisgood.model.SignUpRequest;
import com.getir.readingisgood.repository.CustomerRepository;
import com.getir.readingisgood.security.jwt.JwtUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest {
    @InjectMocks
    private AuthService authService;

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtUtils jwtUtils;

    @Test
    public void signIn() {
        LoginRequest loginRequest = LoginRequest.builder().email("test@test.com").password("test123").build();
        authService.signIn(loginRequest);
        verify(jwtUtils, times(1)).generateJwtToken(any());
    }

    @Test
    public void registerCustomer() {
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .email("test@test.com")
                .password("test123")
                .firstName("test")
                .lastName("test")
                .build();
        authService.registerCustomer(signUpRequest);
        verify(customerRepository, times(1)).save(any());
    }
}