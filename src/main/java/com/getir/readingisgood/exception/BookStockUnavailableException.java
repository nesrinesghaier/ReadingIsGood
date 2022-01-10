package com.getir.readingisgood.exception;

import lombok.Getter;

import javax.persistence.EntityNotFoundException;

@Getter
public class BookStockUnavailableException extends EntityNotFoundException {
    public String bookTitle;

    public BookStockUnavailableException(String msg) {
        super(msg);
    }

    public BookStockUnavailableException(String msg, String bookTitle) {
        super(msg);
        this.bookTitle = bookTitle;
    }

}
