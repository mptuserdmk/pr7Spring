package org.example.demo.repository;

import org.example.demo.model.Portfolio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    Page<Portfolio> findByWorkDescriptionContainingIgnoreCase(String description, Pageable pageable);
}
