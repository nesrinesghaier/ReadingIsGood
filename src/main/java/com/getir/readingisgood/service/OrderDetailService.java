package com.getir.readingisgood.service;

import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.entity.OrderDetail;
import com.getir.readingisgood.model.OrderDetailDto;
import com.getir.readingisgood.repository.BookRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;

@Service
public class OrderDetailService {
    @Resource
    private BookRepository bookRepository;
    @Resource
    private BookService bookService;

    public OrderDetail buildOrderDetails(OrderDetailDto orderDetailDto) {
        Book book = bookRepository.findById(orderDetailDto.getBookId()).orElseThrow(EntityNotFoundException::new);
        if (book.getStock() >= orderDetailDto.getQuantity()) {
            bookService.updateStock(book.getId(), orderDetailDto.getQuantity());
            return OrderDetail.builder()
                    .price(orderDetailDto.getPrice())
                    .quantity(orderDetailDto.getQuantity())
                    .book(book)
                    .build();
        }
        return null;
    }
}
