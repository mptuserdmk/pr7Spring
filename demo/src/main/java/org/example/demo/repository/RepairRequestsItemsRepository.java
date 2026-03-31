package org.example.demo.repository;

import org.example.demo.model.RepairRequestsItemsModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class RepairRequestsItemsRepository {

    private List<RepairRequestsItemsModel> items = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1);

    public RepairRequestsItemsRepository() {
        items.add(new RepairRequestsItemsModel(idCounter.getAndIncrement(), 1, "Экран", 1, 3000));
        items.add(new RepairRequestsItemsModel(idCounter.getAndIncrement(), 1, "Работа мастера", 1, 1500));
    }

    public List<RepairRequestsItemsModel> findAll() {
        return new ArrayList<>(items);
    }

    public RepairRequestsItemsModel findById(int id) {
        return items.stream()
                .filter(i -> i.getIdItem() == id)
                .findFirst()
                .orElse(null);
    }

    public RepairRequestsItemsModel addItem(RepairRequestsItemsModel item) {
        item.setIdItem(idCounter.getAndIncrement());
        items.add(item);
        return item;
    }

    public RepairRequestsItemsModel updateItem(RepairRequestsItemsModel item) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getIdItem() == item.getIdItem()) {
                items.set(i, item);
                return item;
            }
        }
        return null;
    }

    public void deleteItem(int id) {
        items.removeIf(i -> i.getIdItem() == id);
    }
}