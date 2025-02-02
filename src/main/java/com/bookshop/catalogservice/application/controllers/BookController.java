package com.bookshop.catalogservice.application.controllers;

import com.bookshop.catalogservice.domain.model.Book;
import com.bookshop.catalogservice.domain.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<Iterable<Book>> getBooks() {
        final Iterable<Book> books = bookService.fetchBooks();
        log.info("Sent books to client: {}.", books);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/books/{isbn}")
    public Book getByIsbn(@PathVariable String isbn) {
        return bookService.viewBookDetails(isbn);
    }

    @PostMapping("/books")
    public ResponseEntity<Book> create(@RequestBody Book book) {
        final Book catalogBook = bookService.addBookToCatalog(book);
        log.info("Added book '{}' to the catalog.", catalogBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(catalogBook);
    }

    @PutMapping("/books/{isbn}")
    public Book update(@PathVariable String isbn, @RequestBody Book book) {
        final Book updatedBook = bookService.editBookDetails(isbn, book);
        log.info("Successfully updated book: '{}'!", updatedBook);
        return updatedBook;
    }
}
