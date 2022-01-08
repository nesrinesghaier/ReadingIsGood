package com.getir.readingisgood.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "customer")
public class Customer {

    @Id
    private String email;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String address;

}

