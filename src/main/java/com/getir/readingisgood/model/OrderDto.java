package com.getir.readingisgood.model;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class OrderDto {
    private String customerEmail;
    private LocalDateTime orderDateTime;
    List<OrderDetailDto> orderDetails;
}
