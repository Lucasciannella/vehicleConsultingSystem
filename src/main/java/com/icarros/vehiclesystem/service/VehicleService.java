package com.icarros.vehiclesystem.service;

import com.icarros.vehiclesystem.client.FipeClient;
import com.icarros.vehiclesystem.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    private final FipeClient fipeClient;
    private final AmazonService amazonService;

    @Autowired
    public VehicleService(FipeClient fipeClient, AmazonService amazonService) {
        this.fipeClient = fipeClient;
        this.amazonService = amazonService;
    }

    public Vehicle getVehicleByFipeCodeAndYear(String vehicleType, String fipeCode, String year) {
        Vehicle vehicle = fipeClient.getVehicleByFipeCodeAndYear(vehicleType, fipeCode, year);
        amazonService.uploadJsonToAwsS3(vehicle);
        return vehicle;
    }
}