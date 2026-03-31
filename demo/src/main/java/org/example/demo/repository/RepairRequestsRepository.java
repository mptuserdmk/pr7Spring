package org.example.demo.repository;

import org.example.demo.model.RepairRequestsModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class RepairRequestsRepository {

    private List<RepairRequestsModel> requests = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1);

    public RepairRequestsRepository() {
        requests.add(new RepairRequestsModel(idCounter.getAndIncrement(), "Замена экрана", "В процессе", "2024-01-01"));
        requests.add(new RepairRequestsModel(idCounter.getAndIncrement(), "Не включается", "Новая", "2024-02-01"));
    }

    public List<RepairRequestsModel> findAll() {
        return new ArrayList<>(requests);
    }

    public RepairRequestsModel findById(int id) {
        return requests.stream()
                .filter(r -> r.getIdRequest() == id)
                .findFirst()
                .orElse(null);
    }

    public RepairRequestsModel addRequest(RepairRequestsModel request) {
        request.setIdRequest(idCounter.getAndIncrement());
        requests.add(request);
        return request;
    }

    public RepairRequestsModel updateRequest(RepairRequestsModel request) {
        for (int i = 0; i < requests.size(); i++) {
            if (requests.get(i).getIdRequest() == request.getIdRequest()) {
                requests.set(i, request);
                return request;
            }
        }
        return null;
    }

    public void deleteRequest(int id) {
        requests.removeIf(r -> r.getIdRequest() == id);
    }
}