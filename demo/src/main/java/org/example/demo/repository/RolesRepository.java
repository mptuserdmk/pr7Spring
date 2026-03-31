package org.example.demo.repository;

import org.example.demo.model.RolesModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class RolesRepository {

    private final List<RolesModel> roles = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public List<RolesModel> findAll() {
        return new ArrayList<>(roles);
    }

    public RolesModel add(RolesModel role) {
        role.setIdRole(idCounter.getAndIncrement());
        roles.add(role);
        return role;
    }

    public void delete(int id) {
        roles.removeIf(r -> r.getIdRole() == id);
    }
}