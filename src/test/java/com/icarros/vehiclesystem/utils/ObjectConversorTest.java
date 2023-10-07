package com.icarros.vehiclesystem.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icarros.vehiclesystem.builders.VehicleBuilder;
import com.icarros.vehiclesystem.builders.VehicleDtoBuilder;
import com.icarros.vehiclesystem.exception.BadRequestException;
import com.icarros.vehiclesystem.model.Vehicle;
import com.icarros.vehiclesystem.model.VehicleDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ObjectConversorTest {

    private ObjectConversor objectConversor;
    private Vehicle vehicle;
    private VehicleDto vehicleDto;

    @BeforeEach
    void setUp() {
        objectConversor = new ObjectConversor();
        vehicle = VehicleBuilder.aVehicle().withBrand("chevrolet").withModel("celta").withPrice("R$3000.00").build();
        vehicleDto = VehicleDtoBuilder.aVehicleDto().build();
    }

    @Test
    void must_convert_object_when_successful() {
        String wantJson = getObjectToJson(vehicle);

        var json = objectConversor.objectToJson(vehicle);

        Assertions.assertEquals(json, wantJson);
    }

    @Test
    void must_convert_listObject_when_successful() {
        var wantJson = getListObjectToJson(List.of(vehicleDto));

        var json = objectConversor.listVehicleDtoToJson(List.of(vehicleDto));

        Assertions.assertEquals(json, wantJson);
    }

    private String getObjectToJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String getListObjectToJson(List<Object> objects) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<String> jsonList = new ArrayList<>();
            for (Object object : objects) {
                String json = objectMapper.writeValueAsString(object);
                jsonList.add(json);
            }
            return "[" + String.join(",", jsonList) + "]";
        } catch (JsonProcessingException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}