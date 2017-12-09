package com.example.splitty.splitty.Classes;


import java.util.Date;

public class Event {

    private int id;
    private String name;
    private int contactGroupId;
    private int purchaseGroupId;

    public Event() {
    }

    public Event(int id, String name, int contactGroupId, int purchaseGroupId) {
        this.id = id;
        this.name = name;
        this.contactGroupId = contactGroupId;
        this.purchaseGroupId = purchaseGroupId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getContactGroupId() {
        return contactGroupId;
    }

    public void setContactGroupId(int contactGroupId) {
        this.contactGroupId = contactGroupId;
    }

    public int getPurchaseGroupId() {
        return purchaseGroupId;
    }

    public void setPurchaseGroupId(int purchaseGroupId) {
        this.purchaseGroupId = purchaseGroupId;
    }

    public double getTotal(int purchaseGroupId){
        // ...
        return 0;
    }
}