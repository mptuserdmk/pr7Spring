package org.example.demo.service;

import org.example.demo.model.RolesModel;
import org.example.demo.repository.RolesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {

    private final RolesRepository repo;

    public RolesServiceImpl(RolesRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<RolesModel> findAll() {
        return repo.findAll();
    }

    @Override
    public RolesModel addRole(String roleName) {
        return repo.add(new RolesModel(0, roleName));
    }

    @Override
    public void deleteRole(int id) {
        repo.delete(id);
    }
}