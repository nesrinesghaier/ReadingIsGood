package com.getir.readingisgood.service;

import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.entity.Order;
import com.getir.readingisgood.entity.OrderDetail;
import com.getir.readingisgood.exception.BookStockUnavailableException;
import com.getir.readingisgood.model.OrderDetailDto;
import com.getir.readingisgood.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
public class OrderDetailService {
    @Resource
    private BookRepository bookRepository;
    @Resource
    private BookService bookService;

    public OrderDetail buildOrderDetails(OrderDetailDto orderDetailDto, Order order) {
        Book book = bookRepository.findById(orderDetailDto.getBookId()).orElseThrow(EntityNotFoundException::new);
        if (book.getStock() >= orderDetailDto.getQuantity()) {
            bookService.updateStock(book, orderDetailDto.getQuantity());
            return OrderDetail.builder()
                    .quantity(orderDetailDto.getQuantity())
                    .book(book)
                    .order(order)
                    .build();
        }
        throw new BookStockUnavailableException("The stock of the book with title {} is not available in stock anymore", book.getTitle());
    }
}
