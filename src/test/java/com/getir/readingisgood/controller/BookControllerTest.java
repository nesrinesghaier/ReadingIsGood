package com.getir.readingisgood.controller;

import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.model.BookDto;
import com.getir.readingisgood.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Test
    public void addBook() {
        BookDto bookDto = mockBookDto();
        ResponseEntity<Book> response = bookController.addBook(bookDto);
        verify(bookService, times(1)).addBook(any());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void updateBook() {
        BookDto bookDto = mockBookDto();
        ResponseEntity<?> response = bookController.updateBookStock(bookDto);
        verify(bookService, times(1)).updateBookStock(any());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateNonExistingBook() {
        BookDto bookDto = mockBookDto();
        doThrow(new EntityNotFoundException("Book with specified id does not exist")).when(bookService).updateBookStock(any());
        ResponseEntity<?> response = bookController.updateBookStock(bookDto);
        verify(bookService, times(1)).updateBookStock(any());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
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