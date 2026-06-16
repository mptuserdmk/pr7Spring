package org.example.demo.repository;

import org.example.demo.model.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    Page<Service> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
