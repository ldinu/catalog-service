package com.bookshop.catalogservice.domain.model;

public record Book(String isbn, String title, String author, Double price) {
}
