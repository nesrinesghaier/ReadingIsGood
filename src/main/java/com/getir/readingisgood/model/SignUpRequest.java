package com.getir.readingisgood.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SignUpRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Set<String> role;
}
