package com.getir.readingisgood.model;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class BookDto {

    private long id;
    private String title;
    private String author;
    private String type;
    private String publisher;
    private int stock;
}
