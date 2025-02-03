package com.bookshop.catalogservice.domain.services;

import com.bookshop.catalogservice.domain.exceptions.BookException;
import com.bookshop.catalogservice.domain.mappers.BookDtoToEntityMapper;
import com.bookshop.catalogservice.domain.model.BookDto;
import com.bookshop.catalogservice.domain.repositories.BookRepository;
import com.bookshop.catalogservice.domain.repositories.entities.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookDtoToEntityMapper bookDtoToEntityMapper;

    public Iterable<BookDto> fetchBooks() {
        Iterable<Book> books = bookRepository.findAll();
        return bookDtoToEntityMapper.convertBooksToDtos(books);
    }

    public BookDto viewBookDetails(String isbn) {
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        if (optionalBook.isPresent()) {
            return bookDtoToEntityMapper.convertBookToBookDto(optionalBook.get());
        }
        throw new BookException(String.format("The book with '%s' cannot be found!", isbn));
    }

    public BookDto addBookToCatalog(BookDto bookDto) {
        if (bookRepository.exists(bookDto.isbn())) {
            throw new BookException(String.format("The bookDto with ISBN '%s' already exists!", bookDto.isbn()));
        }
        Book book = bookDtoToEntityMapper.convertBookDtoToBook(bookDto);
        Book savedBook = bookRepository.save(book);
        return bookDtoToEntityMapper.convertBookToBookDto(savedBook);
    }

    public void removeBookFromCatalog(String isbn) {
        bookRepository.delete(isbn);
    }

    public BookDto editBookDetails(String isbn, BookDto bookDto) {
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        return optionalBook.map(existingBook -> updateBook(bookDto, existingBook))
                .orElseGet(() -> addBookToCatalog(bookDto));
    }

    private BookDto updateBook(BookDto bookDto, Book existingBook) {
        var bookToUpdate = new BookDto(existingBook.isbn(), bookDto.title(), bookDto.author(), bookDto.price());
        Book book = bookDtoToEntityMapper.convertBookDtoToBook(bookToUpdate);
        Book savedBook = bookRepository.save(book);
        return bookDtoToEntityMapper.convertBookToBookDto(savedBook);
    }

}
