package com.icarros.vehiclesystem.builders;

import com.icarros.vehiclesystem.model.Vehicle;

public final class VehicleBuilder {
    private String brand;
    private String model;
    private String price;

    private VehicleBuilder() {
    }

    public static VehicleBuilder aVehicle() {
        return new VehicleBuilder();
    }

    public VehicleBuilder withBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public VehicleBuilder withModel(String model) {
        this.model = model;
        return this;
    }

    public VehicleBuilder withPrice(String price) {
        this.price = price;
        return this;
    }

    public Vehicle build() {
        return new Vehicle(brand, model, price);
    }
}
