package com.icarros.vehiclesystem.service;

import com.amazonaws.services.s3.AmazonS3;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icarros.vehiclesystem.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AmazonService {
    private final AmazonS3 amazonS3;
    @Value(value = "${aws.s3.endpoint}")
    private String endpoint;

    @Autowired
    public AmazonService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public void uploadJsonToAwsS3(Vehicle vehicle) {
        String json = this.convertVehicleToJson(vehicle);
        String objectKey = generateObjectKey();
        amazonS3.putObject(this.endpoint, objectKey, json);
    }

    private String convertVehicleToJson(Vehicle vehicle) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(vehicle);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateObjectKey() {
        String uniqueKey = UUID.randomUUID().toString();
        return uniqueKey + ".json";
    }
}