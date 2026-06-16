package org.example.demo.service;

import org.example.demo.repository.ServiceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServiceService {
    private final ServiceRepository repository;

    public ServiceService(ServiceRepository repository) {
        this.repository = repository;
    }

    public Page<org.example.demo.model.Service> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<org.example.demo.model.Service> findAll() {
        return repository.findAll();
    }

    public Page<org.example.demo.model.Service> searchByName(String name, Pageable pageable) {
        if (name == null || name.trim().isEmpty()) {
            return repository.findAll(pageable);
        }
        return repository.findByNameContainingIgnoreCase(name.trim(), pageable);
    }

    public org.example.demo.model.Service save(org.example.demo.model.Service service) {
        if (service != null) {
            return repository.save(service);
        }
        throw new IllegalArgumentException("Service cannot be null");
    }

    public org.example.demo.model.Service findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
