package org.example.demo.service;

import org.example.demo.model.PositionsModel;
import java.util.List;

public interface PositionsService {
    List<PositionsModel> findAll();
    PositionsModel findById(int id);
    PositionsModel addPosition(PositionsModel position);
    PositionsModel updatePosition(PositionsModel position);
    void deletePosition(int id);
}