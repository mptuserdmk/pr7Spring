package org.example.demo.model;

public class RepairRequestsItemsModel {
    private int idItem;
    private int requestId;
    private String itemName;
    private int quantity;
    private double price;

    public RepairRequestsItemsModel(int idItem, int requestId, String itemName, int quantity, double price) {
        this.idItem = idItem;
        this.requestId = requestId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}