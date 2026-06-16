package org.example.demo.repository;

import org.example.demo.model.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Page<Appointment> findByStatusContainingIgnoreCase(String status, Pageable pageable);
}
