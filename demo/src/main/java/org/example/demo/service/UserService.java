package org.example.demo.service;

import org.example.demo.model.User;
import org.example.demo.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Page<User> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public Page<User> searchByEmail(String email, Pageable pageable) {
        if (email == null || email.trim().isEmpty()) {
            return repository.findAll(pageable);
        }
        return repository.findByEmailContainingIgnoreCase(email.trim(), pageable);
    }

    public User save(User user) {
        if (user != null) {
            return repository.save(user);
        }
        throw new IllegalArgumentException("User cannot be null");
    }

    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
