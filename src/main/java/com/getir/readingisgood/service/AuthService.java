package com.getir.readingisgood.service;

import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.exception.UserAlreadyExistException;
import com.getir.readingisgood.model.JwtResponse;
import com.getir.readingisgood.model.LoginRequest;
import com.getir.readingisgood.model.SignUpRequest;
import com.getir.readingisgood.repository.CustomerRepository;
import com.getir.readingisgood.security.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AuthService {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private CustomerRepository customerRepository;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtUtils jwtUtils;


    public JwtResponse signIn(LoginRequest request) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return JwtResponse.builder().token(jwt).build();

    }

    public String registerCustomer(SignUpRequest request) {
        boolean userAlreadyExists = customerRepository.existsByEmail(request.getEmail());
        if (userAlreadyExists) {
            throw new UserAlreadyExistException("Error: Email is already in use!");
        }
        Customer customer = Customer.builder().email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName()).build();
        customerRepository.save(customer);
        log.info("Customer registered successfully");
        return "Customer registered successfully!";
    }


}
