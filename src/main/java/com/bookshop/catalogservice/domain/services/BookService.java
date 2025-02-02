package com.bookshop.catalogservice.domain.services;

import com.bookshop.catalogservice.domain.exceptions.BookException;
import com.bookshop.catalogservice.domain.model.Book;
import com.bookshop.catalogservice.domain.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Iterable<Book> fetchBooks() {
        return bookRepository.findAll();
    }

    public Book viewBookDetails(String isbn) {
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        return optionalBook.orElseThrow(() -> new BookException(String.format("The book with '%s' cannot be found!", isbn)));
    }

    public Book addBookToCatalog(Book book) {
        if (bookRepository.exists(book.isbn())) {
            throw new BookException(String.format("The book with ISBN '%s' already exists!", book.isbn()));
        }
        return bookRepository.save(book);
    }

    public void removeBookFromCatalog(String isbn) {
        bookRepository.delete(isbn);
    }

    public Book editBookDetails(String isbn, Book book) {
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        return optionalBook.map(existingBook -> updateBook(book, existingBook))
                .orElseGet(() -> addBookToCatalog(book));
    }

    private Book updateBook(Book book, Book existingBook) {
        var bookToUpdate = new Book(existingBook.isbn(), book.title(), book.author(), book.price());
        return bookRepository.save(bookToUpdate);
    }

}
