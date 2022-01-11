package com.getir.readingisgood.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "orderDetails")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column
    @NotNull
    private int quantity;

    @ManyToOne()
    @JoinColumn(name = "order_id", nullable = false, updatable = false)
    @JsonIgnore()
    private Order order;

    @ManyToOne()
    @JoinColumn(name = "book_id", nullable = false)
    @NotNull
    private Book book;

}

