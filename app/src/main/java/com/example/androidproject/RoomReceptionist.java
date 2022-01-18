package com.example.androidproject;

public class RoomReceptionist {
    private int id,
            capacity,
            priceByDay;
    private String userName;

    public RoomReceptionist(int id, int capacity, int priceByDay, String userName) {
        this.id = id;
        this.capacity = capacity;
        this.priceByDay = priceByDay;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPriceByDay() {
        return priceByDay;
    }

    public void setPriceByDay(int priceByDay) {
        this.priceByDay = priceByDay;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}


