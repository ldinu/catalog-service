package com.bookshop.catalogservice.domain.repositories.entities;

public record Book(String isbn, String title, String author, Double price) {

}
