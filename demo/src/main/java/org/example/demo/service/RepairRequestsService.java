package org.example.demo.service;

import org.example.demo.model.RepairRequestsModel;
import java.util.List;

public interface RepairRequestsService {
    List<RepairRequestsModel> findAll();
    RepairRequestsModel findById(int id);
    RepairRequestsModel addRequest(RepairRequestsModel request);
    RepairRequestsModel updateRequest(RepairRequestsModel request);
    void deleteRequest(int id);
}