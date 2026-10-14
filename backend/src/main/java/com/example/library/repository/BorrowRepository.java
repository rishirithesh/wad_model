package com.example.library.repository;

import com.example.library.model.Borrow;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BorrowRepository extends MongoRepository<Borrow, String> {
}
