package org.example.demo.repository;

import org.example.demo.model.PositionsModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PositionsRepository {

    private List<PositionsModel> positions = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1);

    public PositionsRepository() {
        positions.add(new PositionsModel(idCounter.getAndIncrement(), "Менеджер", 50000));
        positions.add(new PositionsModel(idCounter.getAndIncrement(), "Мастер", 40000));
    }

    public List<PositionsModel> findAll() {
        return new ArrayList<>(positions);
    }

    public PositionsModel findById(int id) {
        return positions.stream()
                .filter(p -> p.getIdPosition() == id)
                .findFirst()
                .orElse(null);
    }

    public PositionsModel addPosition(PositionsModel position) {
        position.setIdPosition(idCounter.getAndIncrement());
        positions.add(position);
        return position;
    }

    public PositionsModel updatePosition(PositionsModel position) {
        for (int i = 0; i < positions.size(); i++) {
            if (positions.get(i).getIdPosition() == position.getIdPosition()) {
                positions.set(i, position);
                return position;
            }
        }
        return null;
    }

    public void deletePosition(int id) {
        positions.removeIf(p -> p.getIdPosition() == id);
    }
}