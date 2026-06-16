package org.example.demo.service;

import org.example.demo.model.Category;
import org.example.demo.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Page<Category> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Page<Category> searchByName(String name, Pageable pageable) {
        if (name == null || name.trim().isEmpty()) {
            return repository.findAll(pageable);
        }
        return repository.findByNameContainingIgnoreCase(name.trim(), pageable);
    }

    public Category save(Category category) {
        if (category != null) {
            return repository.save(category);
        }
        throw new IllegalArgumentException("Category cannot be null");
    }

    public Category findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
