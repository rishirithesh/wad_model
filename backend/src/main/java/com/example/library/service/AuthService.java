package com.example.library.service;

import com.example.library.model.User;
import com.example.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repo;

    // returns false if the username is already taken
    public boolean register(User user) {
        if (repo.findByUsername(user.getUsername()) != null) {
            return false;
        }
        repo.save(user);
        return true;
    }

    public boolean login(User user) {
        User found = repo.findByUsername(user.getUsername());
        return found != null && found.getPassword().equals(user.getPassword());
    }
}
