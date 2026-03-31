package org.example.demo.service;

import org.example.demo.model.RepairRequestsModel;
import org.example.demo.repository.RepairRequestsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairRequestsServiceImpl implements RepairRequestsService {

    private final RepairRequestsRepository repository;

    public RepairRequestsServiceImpl(RepairRequestsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RepairRequestsModel> findAll() {
        return repository.findAll();
    }

    @Override
    public RepairRequestsModel findById(int id) {
        return repository.findById(id);
    }

    @Override
    public RepairRequestsModel addRequest(RepairRequestsModel request) {
        return repository.addRequest(request);
    }

    @Override
    public RepairRequestsModel updateRequest(RepairRequestsModel request) {
        return repository.updateRequest(request);
    }

    @Override
    public void deleteRequest(int id) {
        repository.deleteRequest(id);
    }
}