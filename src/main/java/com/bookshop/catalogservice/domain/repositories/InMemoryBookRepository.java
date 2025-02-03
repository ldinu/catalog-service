package com.bookshop.catalogservice.domain.repositories;

import com.bookshop.catalogservice.domain.model.BookDto;
import com.bookshop.catalogservice.domain.repositories.entities.Book;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryBookRepository implements BookRepository {

    private final Map<String, Book> booksByIsbn = new ConcurrentHashMap<>();

    @Override
    public Iterable<Book> findAll() {
        return booksByIsbn.values();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return exists(isbn) ? Optional.of(booksByIsbn.get(isbn)) : Optional.empty();
    }

    @Override
    public boolean exists(String isbn) {
        return booksByIsbn.get(isbn) != null;
    }

    @Override
    public Book save(Book book) {
        return booksByIsbn.put(book.isbn(), book);
    }

    @Override
    public void delete(String isbn) {
        booksByIsbn.remove(isbn);
    }
}
