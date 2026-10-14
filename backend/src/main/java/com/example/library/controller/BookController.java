package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @PostMapping
    public Book add(@RequestBody Book book) {
        return service.add(book);
    }

    @GetMapping
    public List<Book> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Book getOne(@PathVariable String id) {
        return service.getOne(id);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable String id, @RequestBody Book book) {
        return service.update(id, book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
