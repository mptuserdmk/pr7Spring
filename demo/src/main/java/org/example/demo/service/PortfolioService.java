package org.example.demo.service;

import org.example.demo.model.Portfolio;
import org.example.demo.repository.PortfolioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PortfolioService {
    private final PortfolioRepository repository;

    public PortfolioService(PortfolioRepository repository) {
        this.repository = repository;
    }

    public Page<Portfolio> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Portfolio> findAll() {
        return repository.findAll();
    }

    public Page<Portfolio> searchByDescription(String description, Pageable pageable) {
        if (description == null || description.trim().isEmpty()) {
            return repository.findAll(pageable);
        }
        return repository.findByWorkDescriptionContainingIgnoreCase(description.trim(), pageable);
    }

    public Portfolio save(Portfolio portfolio) {
        if (portfolio != null) {
            return repository.save(portfolio);
        }
        throw new IllegalArgumentException("Portfolio cannot be null");
    }

    public Portfolio findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
