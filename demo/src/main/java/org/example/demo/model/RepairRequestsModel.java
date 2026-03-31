package org.example.demo.model;

public class RepairRequestsModel {
    private int idRequest;
    private String description;
    private String status;
    private String createdAt;

    public RepairRequestsModel(int idRequest, String description, String status, String createdAt) {
        this.idRequest = idRequest;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(int idRequest) {
        this.idRequest = idRequest;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}