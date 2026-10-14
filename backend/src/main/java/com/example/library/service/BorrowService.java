package com.example.library.service;

import com.example.library.model.Borrow;
import com.example.library.repository.BorrowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRepository repo;

    public Borrow borrow(Borrow borrow) {
        borrow.setStatus("Borrowed");
        return repo.save(borrow);
    }

    public List<Borrow> getAll() {
        return repo.findAll();
    }

    public Borrow returnBook(String id) {
        Borrow b = repo.findById(id).orElse(null);
        if (b == null) {
            return null;
        }
        b.setStatus("Returned");
        return repo.save(b);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
