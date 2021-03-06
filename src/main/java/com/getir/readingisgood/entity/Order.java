package com.getir.readingisgood.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.getir.readingisgood.model.EStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private EStatus status;

    @Column
    private LocalDateTime orderDateTime;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderDetail> orderDetails;

    @ManyToOne()
    @JoinColumn(name = "email", nullable = false)
    @JsonIgnore()
    private Customer customer;

}
