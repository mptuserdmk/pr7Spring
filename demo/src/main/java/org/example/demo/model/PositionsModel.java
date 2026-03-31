package org.example.demo.model;

public class PositionsModel {
    private int idPosition;
    private String name;
    private double baseSalary;

    public PositionsModel(int idPosition, String name, double baseSalary) {
        this.idPosition = idPosition;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public int getIdPosition() {
        return idPosition;
    }

    public void setIdPosition(int idPosition) {
        this.idPosition = idPosition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }
}