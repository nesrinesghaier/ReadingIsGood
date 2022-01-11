package com.getir.readingisgood.service;

import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.model.BookDto;
import com.getir.readingisgood.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;

@Service
public class BookService {
    @Resource
    private BookRepository bookRepository;

    public Book addBook(BookDto bookDto) {
        Book bookToBeAdded = buildBookFromDto(bookDto);
        return bookRepository.save(bookToBeAdded);
    }

    public Book updateBookStock(BookDto bookDto) {
        Book book = bookRepository.findById(bookDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Book with specified id does not exist"));
        book.setStock(bookDto.getStock());
        return bookRepository.save(book);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateStock(Book book, int quantity) {
        book.setStock(book.getStock() - quantity);
        bookRepository.save(book);
    }

    private Book buildBookFromDto(BookDto bookDto) {
        return Book.builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .publisher(bookDto.getPublisher())
                .type(bookDto.getType())
                .stock(bookDto.getStock())
                .price(bookDto.getPrice())
                .build();
    }
}