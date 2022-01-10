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

    public void addBook(BookDto bookDto) {
        Book bookToBeAdded = buildBookFromDto(bookDto);
        bookRepository.save(bookToBeAdded);
    }

    public void updateBookStock(BookDto bookDto) {
        Book book = bookRepository.findById(bookDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Book with specified id does not exist"));
        book.setStock(bookDto.getStock());
        bookRepository.save(book);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateStock(long id, int quantity) {
        Book book = bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        book.setStock(book.getStock() - quantity);
    }

    private Book buildBookFromDto(BookDto bookDto) {
        return Book.builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .publisher(bookDto.getPublisher())
                .type(bookDto.getType())
                .stock(bookDto.getStock())
                .build();
    }
}