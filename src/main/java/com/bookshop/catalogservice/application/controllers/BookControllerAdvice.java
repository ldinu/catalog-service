package com.bookshop.catalogservice.application.controllers;

import com.bookshop.catalogservice.domain.exceptions.BookException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class BookControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException() {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BookException.class)
    public ResponseEntity<String> handleBookException(BookException bookException) {
        return new ResponseEntity<>(bookException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
