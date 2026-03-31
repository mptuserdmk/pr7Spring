package org.example.demo.model;

public class RepairServicesModel {
    private int idService;
    private String name;
    private double price;

    public RepairServicesModel(int idService, String name, double price) {
        this.idService = idService;
        this.name = name;
        this.price = price;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}