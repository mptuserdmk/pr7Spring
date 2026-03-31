package org.example.demo.service;

import org.example.demo.model.PositionsModel;
import org.example.demo.repository.PositionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionsServiceImpl implements PositionsService {

    private final PositionsRepository positionsRepository;

    public PositionsServiceImpl(PositionsRepository positionsRepository) {
        this.positionsRepository = positionsRepository;
    }

    @Override
    public List<PositionsModel> findAll() {
        return positionsRepository.findAll();
    }

    @Override
    public PositionsModel findById(int id) {
        return positionsRepository.findById(id);
    }

    @Override
    public PositionsModel addPosition(PositionsModel position) {
        return positionsRepository.addPosition(position);
    }

    @Override
    public PositionsModel updatePosition(PositionsModel position) {
        return positionsRepository.updatePosition(position);
    }

    @Override
    public void deletePosition(int id) {
        positionsRepository.deletePosition(id);
    }
}