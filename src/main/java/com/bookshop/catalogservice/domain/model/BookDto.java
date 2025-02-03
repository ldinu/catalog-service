package com.bookshop.catalogservice.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record BookDto(
        @NotBlank(message = "The book ISBN must not be empty!")
        @Pattern(regexp = "^([0-9]{10}|[0-9]{13})$", message = "The ISBN format must be valid")
        String isbn,

        @NotBlank(message = "The book title must not be empty")
        String title,

        @NotBlank(message = "The book author must not be empty")
        String author,

        @NotNull(message = "The book price must be defined")
        @Positive(message = "The book price must be greater than zero")
        Double price
) {
}


