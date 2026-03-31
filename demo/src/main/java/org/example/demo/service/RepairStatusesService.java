package org.example.demo.service;

import org.example.demo.model.RepairStatusesModel;

import java.util.List;

public interface RepairStatusesService {
    List<RepairStatusesModel> findAll();
    RepairStatusesModel add(RepairStatusesModel s);
    void delete(int id);
}
