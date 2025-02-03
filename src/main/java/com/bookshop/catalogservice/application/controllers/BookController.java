package com.bookshop.catalogservice.application.controllers;

import com.bookshop.catalogservice.domain.model.BookDto;
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
    public ResponseEntity<Iterable<BookDto>> getBooks() {
        final Iterable<BookDto> books = bookService.fetchBooks();
        log.info("Sent books to client: {}.", books);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/books/{isbn}")
    public ResponseEntity<BookDto> getByIsbn(@PathVariable String isbn) {
        BookDto book = bookService.viewBookDetails(isbn);
        return ResponseEntity.ok(book);
    }

    @PostMapping("/books")
    public ResponseEntity<BookDto> create(@RequestBody BookDto book) {
        final BookDto catalogBook = bookService.addBookToCatalog(book);
        log.info("Added book '{}' to the catalog.", catalogBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(catalogBook);
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> update(@PathVariable String isbn, @RequestBody BookDto book) {
        final BookDto updatedBook = bookService.editBookDetails(isbn, book);
        log.info("Successfully updated book: '{}'!", updatedBook);
        return ResponseEntity.ok(updatedBook);
    }
}
