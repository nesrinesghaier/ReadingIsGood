package com.getir.readingisgood.model;


import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class OrderDto {
    private String customerEmail;
    List<OrderDetailDto> orderDetails;
}
