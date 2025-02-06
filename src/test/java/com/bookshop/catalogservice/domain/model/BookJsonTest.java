package com.bookshop.catalogservice.domain.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class BookJsonTest {

    @Autowired
    private JacksonTester<BookDto> jacksonTester;

    @Test
    void testSerialize() throws IOException {
        var book = new BookDto("1234567890", "Opera Omnia", "Epictetus", 89.99);
        JsonContent<BookDto> jsonContent = jacksonTester.write(book);
        assertThat(jsonContent).extractingJsonPathValue("@.isbn").isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathValue("@.title").isEqualTo(book.title());
        assertThat(jsonContent).extractingJsonPathValue("@.author").isEqualTo(book.author());
        assertThat(jsonContent).extractingJsonPathValue("@.price").isEqualTo(book.price());
    }

    @Test
    void testDeserialize() throws IOException {
        String json = """
                {
                    "isbn": "1234567890",
                    "title": "Opera Omnia",
                    "author": "Epictetus",
                    "price": 89.99
                }
                """;
        assertThat(jacksonTester.parse(json))
                .usingRecursiveComparison()
                .isEqualTo(new BookDto("1234567890", "Opera Omnia", "Epictetus", 89.99));
    }
}
