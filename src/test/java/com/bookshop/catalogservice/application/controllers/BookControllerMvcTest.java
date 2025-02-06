package com.bookshop.catalogservice.application.controllers;

import com.bookshop.catalogservice.domain.exceptions.BookException;
import com.bookshop.catalogservice.domain.services.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Test
    void whenGetBookNotExistingThenShouldReturn404() throws Exception {
        final String isbn = "1234567890";
        given(bookService.viewBookDetails(isbn))
                .willThrow(BookException.class);
        mockMvc.perform(get("/api/v1/books/" + isbn))
                .andExpect(status().isBadRequest());

    }


}
