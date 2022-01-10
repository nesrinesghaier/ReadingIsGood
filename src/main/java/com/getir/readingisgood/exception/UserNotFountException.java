package com.getir.readingisgood.exception;

import javax.persistence.EntityNotFoundException;

public class UserNotFountException extends EntityNotFoundException {
    public UserNotFountException(String msg) {
        super(msg);
    }
}
