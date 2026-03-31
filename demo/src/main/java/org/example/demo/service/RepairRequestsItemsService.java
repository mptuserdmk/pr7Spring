package org.example.demo.service;

import org.example.demo.model.RepairRequestsItemsModel;
import java.util.List;

public interface RepairRequestsItemsService {
    List<RepairRequestsItemsModel> findAll();
    RepairRequestsItemsModel findById(int id);
    RepairRequestsItemsModel addItem(RepairRequestsItemsModel item);
    RepairRequestsItemsModel updateItem(RepairRequestsItemsModel item);
    void deleteItem(int id);
}