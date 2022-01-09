package com.getir.readingisgood.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "customers", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class Customer {

    @Id
    @Email
    @Size(max = 50)
    private String username;

    @Column
    @NotBlank
    @Size(max = 120)
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    List<Order> orders;

}

