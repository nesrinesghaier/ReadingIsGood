package com.getir.readingisgood.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsDto {

    private String month;
    private long orderCount;
    private long purchasedBooksCount;
    private double purchasedOrdersAmount;

    public StatisticsDto(String month, double purchasedOrdersAmount, long purchasedBooksCount) {
        this.month = month.trim();
        this.purchasedBooksCount = purchasedBooksCount;
        this.purchasedOrdersAmount = purchasedOrdersAmount;
    }

    public StatisticsDto(String month, long orderCount) {
        this.month = month.trim();
        this.orderCount = orderCount;
    }
}
