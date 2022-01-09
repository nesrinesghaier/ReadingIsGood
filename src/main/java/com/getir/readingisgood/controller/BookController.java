package com.getir.readingisgood.controller;

import com.getir.readingisgood.model.BookDto;
import com.getir.readingisgood.service.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("/book")
public class BookController {

    @Resource
    private BookService bookService;

    @PostMapping("")
    public String addBook(@RequestBody BookDto bookDto) {
        bookService.addBook(bookDto);
        return "Book added";
    }

    @PutMapping("")
    public String updateBook(@RequestBody BookDto bookDto) {
        bookService.updateBook(bookDto);
        return "Book updated";
    }


}