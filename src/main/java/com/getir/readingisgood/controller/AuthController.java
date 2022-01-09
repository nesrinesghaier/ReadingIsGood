package com.getir.readingisgood.controller;

import com.getir.readingisgood.model.JwtResponse;
import com.getir.readingisgood.model.LoginRequest;
import com.getir.readingisgood.model.SignUpRequest;
import com.getir.readingisgood.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Resource
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequest request) throws Exception {
        JwtResponse jwtResponse = authService.authenticateUser(request);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authService.registerUser(request));
    }


}
