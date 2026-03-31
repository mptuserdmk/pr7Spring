package org.example.demo.repository;

import org.example.demo.model.DeviceTypesModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class DeviceTypesRepository {

    private final List<DeviceTypesModel> deviceTypes = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    // Конструктор с примерами
    public DeviceTypesRepository() {
        deviceTypes.add(new DeviceTypesModel(idCounter.getAndIncrement(), "Смартфон"));
        deviceTypes.add(new DeviceTypesModel(idCounter.getAndIncrement(), "Ноутбук"));
        deviceTypes.add(new DeviceTypesModel(idCounter.getAndIncrement(), "Планшет"));
    }

    public List<DeviceTypesModel> findAll() {
        return new ArrayList<>(deviceTypes);
    }

    public DeviceTypesModel findById(int id) {
        return deviceTypes.stream()
                .filter(dt -> dt.getIdDeviceType() == id)
                .findFirst()
                .orElse(null);
    }

    public DeviceTypesModel add(DeviceTypesModel deviceType) {
        deviceType.setIdDeviceType(idCounter.getAndIncrement());
        deviceTypes.add(deviceType);
        return deviceType;
    }

    public DeviceTypesModel update(DeviceTypesModel deviceType) {
        for (int i = 0; i < deviceTypes.size(); i++) {
            if (deviceTypes.get(i).getIdDeviceType() == deviceType.getIdDeviceType()) {
                deviceTypes.set(i, deviceType);
                return deviceType;
            }
        }
        return null;
    }

    public void delete(int id) {
        deviceTypes.removeIf(dt -> dt.getIdDeviceType() == id);
    }
}