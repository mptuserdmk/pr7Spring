package org.example.demo.repository;

import org.example.demo.model.RepairServicesModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class RepairServicesRepository {

    private List<RepairServicesModel> services = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1);

    public RepairServicesRepository() {
        services.add(new RepairServicesModel(idCounter.getAndIncrement(), "Замена экрана", 3000));
        services.add(new RepairServicesModel(idCounter.getAndIncrement(), "Чистка устройства", 1000));
    }

    public List<RepairServicesModel> findAll() {
        return new ArrayList<>(services);
    }

    public RepairServicesModel findById(int id) {
        return services.stream()
                .filter(s -> s.getIdService() == id)
                .findFirst()
                .orElse(null);
    }

    public RepairServicesModel addService(RepairServicesModel service) {
        service.setIdService(idCounter.getAndIncrement());
        services.add(service);
        return service;
    }

    public RepairServicesModel updateService(RepairServicesModel service) {
        for (int i = 0; i < services.size(); i++) {
            if (services.get(i).getIdService() == service.getIdService()) {
                services.set(i, service);
                return service;
            }
        }
        return null;
    }

    public void deleteService(int id) {
        services.removeIf(s -> s.getIdService() == id);
    }
}