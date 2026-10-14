package com.example.library.controller;

import com.example.library.model.Borrow;
import com.example.library.service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrows")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowService service;

    @PostMapping
    public Borrow borrow(@RequestBody Borrow borrow) {
        return service.borrow(borrow);
    }

    @GetMapping
    public List<Borrow> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}/return")
    public Borrow returnBook(@PathVariable String id) {
        return service.returnBook(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
