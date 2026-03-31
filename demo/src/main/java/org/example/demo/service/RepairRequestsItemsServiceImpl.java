package org.example.demo.service;

import org.example.demo.model.RepairRequestsItemsModel;
import org.example.demo.repository.RepairRequestsItemsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairRequestsItemsServiceImpl implements RepairRequestsItemsService {

    private final RepairRequestsItemsRepository repository;

    public RepairRequestsItemsServiceImpl(RepairRequestsItemsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RepairRequestsItemsModel> findAll() {
        return repository.findAll();
    }

    @Override
    public RepairRequestsItemsModel findById(int id) {
        return repository.findById(id);
    }

    @Override
    public RepairRequestsItemsModel addItem(RepairRequestsItemsModel item) {
        return repository.addItem(item);
    }

    @Override
    public RepairRequestsItemsModel updateItem(RepairRequestsItemsModel item) {
        return repository.updateItem(item);
    }

    @Override
    public void deleteItem(int id) {
        repository.deleteItem(id);
    }
}