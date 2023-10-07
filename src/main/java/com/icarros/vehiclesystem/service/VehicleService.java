package com.icarros.vehiclesystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icarros.vehiclesystem.client.FipeClient;
import com.icarros.vehiclesystem.infra.FileHandler;
import com.icarros.vehiclesystem.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    private final FipeClient fipeClient;
    private final FileHandler amazonFileHandler;

    @Autowired
    public VehicleService(FipeClient fipeClient, FileHandler amazonFileHandler) {
        this.fipeClient = fipeClient;
        this.amazonFileHandler = amazonFileHandler;
    }

    public Vehicle getCarByFipeCodeAndYear(String fipeCode, String year) {
        Vehicle vehicle = fipeClient.getCarByFipeCodeAndYear(fipeCode, year);
        String json = convertVehicleToJson(vehicle);
        amazonFileHandler.save(json);
        return vehicle;
    }

    private String convertVehicleToJson(Vehicle vehicle) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(vehicle);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}