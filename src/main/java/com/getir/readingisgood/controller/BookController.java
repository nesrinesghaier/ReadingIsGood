package com.getir.readingisgood.controller;

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
    public ResponseEntity<String> addBook(@RequestBody BookDto bookDto) {
        bookService.addBook(bookDto);
        log.info("Book added successfully");
        return new ResponseEntity<>("Book added successfully",HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<String> updateBookStock(@RequestBody BookDto bookDto) {
        try {
            bookService.updateBookStock(bookDto);
            return ResponseEntity.ok("Book stock updated successfully");
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}