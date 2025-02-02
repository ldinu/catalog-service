package com.bookshop.catalogservice.domain.exceptions;

import lombok.Getter;

@Getter
public class BookException extends RuntimeException {

    private final String message;

    public BookException(String message) {
        super(message);
        this.message = message;
    }
}
