package com.example.splitty.splitty.Classes;

import java.util.Date;

public class Purchase {

    private int id;
    private String desc;
    private int buyerId;
    private double cost;

    public Purchase() {
    }

    public Purchase(int id, String desc, int buyerId, double cost) {
        this.id = id;
        this.desc = desc;
        this.buyerId = buyerId;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}