package org.example.demo.service;

import org.example.demo.model.RepairStatusesModel;
import org.example.demo.repository.RepairStatusesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairStatusesServiceImpl implements RepairStatusesService {

    private final RepairStatusesRepository repo;

    public RepairStatusesServiceImpl(RepairStatusesRepository repo) {
        this.repo = repo;
    }

    public List<RepairStatusesModel> findAll() { return repo.findAll(); }
    public RepairStatusesModel add(RepairStatusesModel s) { return repo.add(s); }
    public void delete(int id) { repo.delete(id); }
}
