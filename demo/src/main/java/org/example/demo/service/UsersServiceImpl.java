package org.example.demo.service;

import org.example.demo.model.UsersModel;
import org.example.demo.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository repo;

    public UsersServiceImpl(UsersRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<UsersModel> findAll() {
        return repo.findAll();
    }

    @Override
    public UsersModel addUser(String username, String email, int roleId) {
        return repo.add(new UsersModel(0, username, email, roleId));
    }

    @Override
    public void deleteUser(int id) {
        repo.delete(id);
    }
}