package com.getir.readingisgood.controller;

import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.model.BookDto;
import com.getir.readingisgood.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;

@RestController()
@RequestMapping("/api/book")
@Slf4j
public class BookController {

    @Resource
    private BookService bookService;

    @PostMapping("")
    public ResponseEntity<Book> addBook(@RequestBody BookDto bookDto) {
        Book book = bookService.addBook(bookDto);
        log.info("Book added successfully");
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<?> updateBookStock(@RequestBody BookDto bookDto) {
        try {
            Book book = bookService.updateBookStock(bookDto);
            log.info("Book stock updated successfully");
            return ResponseEntity.ok(book);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}