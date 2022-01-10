package com.getir.readingisgood.service;

import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.model.BookDto;
import com.getir.readingisgood.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void addBookTest() {
        BookDto bookDto = mockBookDto();
        bookService.addBook(bookDto);
        verify(bookRepository, times(1)).save(any());
    }

    @Test
    public void updateBookTest() {
        BookDto bookDto = mockBookDto();
        when(bookRepository.findById(any())).thenReturn(Optional.of(Book.builder().build()));
        bookService.updateBook(bookDto);
        verify(bookRepository, times(1)).save(any());
    }

    @Test
    public void updateStockTest() {
        when(bookRepository.findById(any())).thenReturn(Optional.of(Book.builder().build()));
        bookService.updateStock(1,5);
        verify(bookRepository, times(1)).findById(any());
    }

    BookDto mockBookDto() {
        return BookDto.builder()
                .id(1)
                .title("test")
                .author("test")
                .publisher("test")
                .type("test")
                .stock(2)
                .build();
    }
}