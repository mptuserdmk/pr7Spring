package org.example.demo.model;

public class DeviceTypesModel {
    private int idDeviceType;
    private String deviceTypeName;

    public DeviceTypesModel(int idDeviceType, String deviceTypeName) {
        this.idDeviceType = idDeviceType;
        this.deviceTypeName = deviceTypeName;
    }

    public int getIdDeviceType() {
        return idDeviceType;
    }

    public void setIdDeviceType(int idDeviceType) {
        this.idDeviceType = idDeviceType;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }
}