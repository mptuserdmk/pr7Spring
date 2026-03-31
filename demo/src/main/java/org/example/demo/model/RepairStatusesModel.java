package org.example.demo.model;

public class RepairStatusesModel {
    private int idStatus;
    private String name;

    public RepairStatusesModel(int idStatus, String name) {
        this.idStatus = idStatus;
        this.name = name;
    }

    public int getIdStatus() { return idStatus; }
    public void setIdStatus(int idStatus) { this.idStatus = idStatus; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}