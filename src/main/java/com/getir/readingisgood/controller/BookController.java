package com.getir.readingisgood.controller;

import com.getir.readingisgood.model.BookDto;
import com.getir.readingisgood.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController()
@RequestMapping("/api/book")
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