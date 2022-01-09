package com.getir.readingisgood.controller;

import com.getir.readingisgood.model.JwtResponse;
import com.getir.readingisgood.model.LoginRequest;
import com.getir.readingisgood.model.SignUpRequest;
import com.getir.readingisgood.service.AuthService;
import com.getir.readingisgood.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping()
public class CustomerController {

    @Resource
    private AuthService authService;
    @Resource
    private CustomerService customerService;

    @PostMapping("/auth/signin")
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequest request) {
        JwtResponse jwtResponse = authService.authenticateUser(request);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authService.registerUser(request));
    }

    @GetMapping("/api/{username}")
    public ResponseEntity<?> getCustomerOrders(@PathVariable String username) {
        return ResponseEntity.ok(customerService.getCustomerOrders(username));
    }


}
