package org.example.demo.repository;

import org.example.demo.model.RepairStatusesModel;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class RepairStatusesRepository {

    private List<RepairStatusesModel> statuses = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1);

    public RepairStatusesRepository() {
        statuses.add(new RepairStatusesModel(idCounter.getAndIncrement(), "Новая"));
        statuses.add(new RepairStatusesModel(idCounter.getAndIncrement(), "В процессе"));
    }

    public List<RepairStatusesModel> findAll() { return statuses; }

    public RepairStatusesModel add(RepairStatusesModel s) {
        s.setIdStatus(idCounter.getAndIncrement());
        statuses.add(s);
        return s;
    }

    public void delete(int id) {
        statuses.removeIf(s -> s.getIdStatus() == id);
    }
}