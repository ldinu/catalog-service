package com.bookshop.catalogservice.domain.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BookValidatorTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {
        var book = new BookDto("1234567890", "Meditations", "Marcus Aurelius", 19.99);
        Set<ConstraintViolation<BookDto>> violations = validator.validate(book);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenIsbnIncorrectThenValidationFails() {
        var book = new BookDto("bubu", "Meditations", "Marcus Aurelius", 19.99);
        Set<ConstraintViolation<BookDto>> violations = validator.validate(book);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("The ISBN format must be valid");
    }
}
