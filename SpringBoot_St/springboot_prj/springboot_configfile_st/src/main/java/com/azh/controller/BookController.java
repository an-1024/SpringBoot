package com.azh.controller;

import com.azh.config.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BookController {
    @Autowired
    Book book;

    @GetMapping("/book")
    public String book() {
        System.out.println("--------" + book.toString());
        return book.toString();
    }
}
