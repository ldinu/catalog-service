package com.bookshop.catalogservice;

import com.bookshop.catalogservice.domain.model.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogServiceApplicationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void whenPostReceivedThenBookCreated() {
        var expectedBook = new BookDto("1234567890", "Meditations", "Marcus Aurelius", 39.99);
        webTestClient.post()
                .uri("/api/v1/books")
                .bodyValue(expectedBook)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(BookDto.class).value(actualBook -> assertResponse(actualBook, expectedBook));
    }

    private void assertResponse(BookDto actualBook, BookDto expectedBook) {
        assertThat(actualBook).isNotNull();
        assertThat(actualBook.isbn()).isEqualTo(expectedBook.isbn());
    }
}
