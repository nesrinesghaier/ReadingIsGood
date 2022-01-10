package com.getir.readingisgood.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class OrderDetailDto {
    private double price;
    private int quantity;
    private long bookId;
    private long orderId;
}
