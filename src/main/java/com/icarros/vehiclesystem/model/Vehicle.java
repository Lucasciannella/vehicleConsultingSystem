package com.icarros.vehiclesystem.model;

public class Vehicle {
    private String brand;
    private String model;
    private String price;


    public Vehicle(String brand, String model, String price) {
        this.brand = brand;
        this.model = model;
        this.price = price;

    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getPrice() {
        return price;
    }
}