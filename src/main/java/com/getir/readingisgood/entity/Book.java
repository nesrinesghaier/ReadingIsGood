package com.getir.readingisgood.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotBlank
    private String title;

    @Column
    @NotBlank
    private String author;

    @Column
    private String type;

    @Column
    private String publisher;

    @Column
    @NotBlank
    private int stock;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    @JsonIgnore()
    List<OrderDetail> orderDetails;

    @Version
    @JsonIgnore()
    private Long version;

}