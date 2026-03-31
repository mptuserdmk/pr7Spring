package org.example.demo.service;

import org.example.demo.model.DeviceTypesModel;

import java.util.List;

public interface DeviceTypesService {
    List<DeviceTypesModel> findAll();
    DeviceTypesModel addDeviceType(DeviceTypesModel deviceType);
    DeviceTypesModel updateDeviceType(DeviceTypesModel deviceType);
    void deleteDeviceType(int id);
}