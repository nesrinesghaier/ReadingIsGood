package com.getir.readingisgood.integration;

import com.getir.readingisgood.controller.BookController;
import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.model.BookDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookControllerIntegrationTests {

    @Resource
    BookController bookController;

    @Test
    void testAddBook() {
        BookDto bookDto = mockBookDto();
        ResponseEntity<Book> response = bookController.addBook(bookDto);
        assertThat(response).hasFieldOrPropertyWithValue("status", HttpStatus.CREATED);
        long bookId = Objects.requireNonNull(response.getBody()).getId();
        assertThat(bookId).isPositive();


    }

    @Test
    void testUpdateBookStock() {
        BookDto bookDto = mockBookDto();
        ResponseEntity<Book> response = bookController.addBook(bookDto);
        assertThat(response).hasFieldOrPropertyWithValue("status", HttpStatus.CREATED);
        long bookId = Objects.requireNonNull(response.getBody()).getId();

        bookDto.setId(bookId);
        bookDto.setStock(1);
        ResponseEntity<?> updateBookStockResponse = bookController.updateBookStock(bookDto);

        assertThat(updateBookStockResponse).hasFieldOrPropertyWithValue("status", HttpStatus.OK);
        assertThat(((Book) (Objects.requireNonNull(updateBookStockResponse.getBody()))).getStock()).isEqualTo(1);
    }

    BookDto mockBookDto() {
        return BookDto.builder()
                .title("test")
                .author("test")
                .publisher("test")
                .type("test")
                .stock(20)
                .price(19.9)
                .build();
    }
}
