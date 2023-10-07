package com.icarros.vehiclesystem.model;

public record FipeDto(String price,
                      String brand,
                      String model,
                      Integer modelYear,
                      String fuel,
                      String codeFipe,
                      String referenceMonth,
                      Integer vehicleType,
                      String fuelAcronym) {
}