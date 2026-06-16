package org.example.demo.service;

import org.example.demo.model.Appointment;
import org.example.demo.repository.AppointmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public Page<Appointment> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Appointment> findAll() {
        return repository.findAll();
    }

    public Page<Appointment> searchByStatus(String status, Pageable pageable) {
        if (status == null || status.trim().isEmpty()) {
            return repository.findAll(pageable);
        }
        return repository.findByStatusContainingIgnoreCase(status.trim(), pageable);
    }

    public Appointment save(Appointment appointment) {
        if (appointment != null) {
            return repository.save(appointment);
        }
        throw new IllegalArgumentException("Appointment cannot be null");
    }
    
    public Appointment findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
