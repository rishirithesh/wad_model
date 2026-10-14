package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repo;

    public Book add(Book book) {
        return repo.save(book);
    }

    public List<Book> getAll() {
        return repo.findAll();
    }

    public Book getOne(String id) {
        return repo.findById(id).orElse(null);
    }

    public Book update(String id, Book book) {
        book.setId(id);
        return repo.save(book);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
