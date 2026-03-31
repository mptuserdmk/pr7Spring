package org.example.demo.service;

import org.example.demo.model.RepairServicesModel;
import org.example.demo.repository.RepairServicesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairServicesServiceImpl implements RepairServicesService {

    private final RepairServicesRepository repository;

    public RepairServicesServiceImpl(RepairServicesRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RepairServicesModel> findAll() {
        return repository.findAll();
    }

    @Override
    public RepairServicesModel findById(int id) {
        return repository.findById(id);
    }

    @Override
    public RepairServicesModel addService(RepairServicesModel service) {
        return repository.addService(service);
    }

    @Override
    public RepairServicesModel updateService(RepairServicesModel service) {
        return repository.updateService(service);
    }

    @Override
    public void deleteService(int id) {
        repository.deleteService(id);
    }
}