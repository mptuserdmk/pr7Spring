package org.example.demo.repository;

import org.example.demo.model.UsersModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UsersRepository {

    private final List<UsersModel> users = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public List<UsersModel> findAll() {
        return new ArrayList<>(users);
    }

    public UsersModel add(UsersModel user) {
        user.setIdUser(idCounter.getAndIncrement());
        users.add(user);
        return user;
    }

    public void delete(int id) {
        users.removeIf(u -> u.getIdUser() == id);
    }
}