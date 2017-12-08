package com.example.splitty.splitty.Classes;

public class PurchaseGroup {

    private int id;
    private int purchaseId;
    private double eventId;

    public PurchaseGroup(){

    }

    public PurchaseGroup(int id, int purchaseId, double eventId) {
        this.id = id;
        this.purchaseId = purchaseId;
        this.eventId = eventId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public double getEventId() {
        return eventId;
    }

    public void setEventId(double eventId) {
        this.eventId = eventId;
    }
}