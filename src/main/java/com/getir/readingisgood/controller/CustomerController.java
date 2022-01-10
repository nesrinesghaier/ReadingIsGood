package com.getir.readingisgood.controller;

import com.getir.readingisgood.exception.UserAlreadyExistException;
import com.getir.readingisgood.model.JwtResponse;
import com.getir.readingisgood.model.LoginRequest;
import com.getir.readingisgood.model.SignUpRequest;
import com.getir.readingisgood.service.AuthService;
import com.getir.readingisgood.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping()
@Slf4j
public class CustomerController {

    @Resource
    private AuthService authService;
    @Resource
    private CustomerService customerService;

    @PostMapping("/auth/signin")
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequest request) {
        try {
            JwtResponse jwtResponse = authService.signIn(request);
            return ResponseEntity.ok(jwtResponse);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Password or/and email is wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequest request) {
        try {
            return ResponseEntity.ok(authService.registerCustomer(request));
        } catch (UserAlreadyExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/orders")
    public ResponseEntity<?> getCustomerOrders(@RequestParam String email, @RequestParam(defaultValue = "0") int page) {
        try {
            return new ResponseEntity<>(customerService.getCustomerOrders(email, page), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
