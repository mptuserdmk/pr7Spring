package org.example.demo.service;

import org.example.demo.model.RolesModel;
import java.util.List;

public interface RolesService {
    List<RolesModel> findAll();
    RolesModel addRole(String roleName);
    void deleteRole(int id);
}