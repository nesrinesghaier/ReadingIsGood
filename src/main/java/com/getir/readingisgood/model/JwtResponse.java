package com.getir.readingisgood.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class JwtResponse {
    private String token;
    private String username;
    private String type = "Bearer";
    private List roles;
}
