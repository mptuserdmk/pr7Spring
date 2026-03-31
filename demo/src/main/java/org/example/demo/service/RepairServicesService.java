package org.example.demo.service;

import org.example.demo.model.RepairServicesModel;
import java.util.List;

public interface RepairServicesService {
    List<RepairServicesModel> findAll();
    RepairServicesModel findById(int id);
    RepairServicesModel addService(RepairServicesModel service);
    RepairServicesModel updateService(RepairServicesModel service);
    void deleteService(int id);
}