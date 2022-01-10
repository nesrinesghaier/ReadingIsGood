package com.getir.readingisgood.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignUpRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
