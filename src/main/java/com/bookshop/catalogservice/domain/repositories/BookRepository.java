package com.bookshop.catalogservice.domain.repositories;

import com.bookshop.catalogservice.domain.model.Book;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface BookRepository {

    Iterable<Book> findAll();

    Optional<Book> findByIsbn(String isbn);

    boolean exists(String isbn);

    Book save(Book book);

    void delete(String isbn);
}
