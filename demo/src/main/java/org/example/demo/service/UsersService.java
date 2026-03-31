package org.example.demo.service;

import org.example.demo.model.UsersModel;
import java.util.List;

public interface UsersService {
    List<UsersModel> findAll();
    UsersModel addUser(String username, String email, int roleId);
    void deleteUser(int id);
}