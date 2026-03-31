package org.example.demo.service;

import org.example.demo.model.DeviceTypesModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class DeviceTypesServiceImpl implements DeviceTypesService {

    private List<DeviceTypesModel> deviceTypes = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1);

    public DeviceTypesServiceImpl() {
        // Пример данных
        deviceTypes.add(new DeviceTypesModel(idCounter.getAndIncrement(), "Смартфон"));
        deviceTypes.add(new DeviceTypesModel(idCounter.getAndIncrement(), "Ноутбук"));
        deviceTypes.add(new DeviceTypesModel(idCounter.getAndIncrement(), "Планшет"));
    }

    @Override
    public List<DeviceTypesModel> findAll() {
        return new ArrayList<>(deviceTypes);
    }

    @Override
    public DeviceTypesModel addDeviceType(DeviceTypesModel deviceType) {
        deviceType.setIdDeviceType(idCounter.getAndIncrement());
        deviceTypes.add(deviceType);
        return deviceType;
    }

    @Override
    public DeviceTypesModel updateDeviceType(DeviceTypesModel deviceType) {
        for (int i = 0; i < deviceTypes.size(); i++) {
            if (deviceTypes.get(i).getIdDeviceType() == deviceType.getIdDeviceType()) {
                deviceTypes.set(i, deviceType);
                return deviceType;
            }
        }
        return null;
    }

    @Override
    public void deleteDeviceType(int id) {
        deviceTypes.removeIf(d -> d.getIdDeviceType() == id);
    }
}