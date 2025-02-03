package com.bookshop.catalogservice.domain.mappers;

import com.bookshop.catalogservice.domain.model.BookDto;
import com.bookshop.catalogservice.domain.repositories.entities.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookDtoToEntityMapper {

    BookDto convertBookToBookDto(Book book);

    Book convertBookDtoToBook(BookDto bookDto);

    Iterable<Book> convertDtosToBooks(Iterable<BookDto> dtos);

    Iterable<BookDto> convertBooksToDtos(Iterable<Book> books);


}
