package com.bookshop.catalogservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class HomeController {

    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Greetings!");
    }
}
