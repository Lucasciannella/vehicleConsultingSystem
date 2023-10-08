package com.icarros.vehiclesystem.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icarros.vehiclesystem.exception.BadRequestException;
import com.icarros.vehiclesystem.model.VehicleDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ObjectConversor {

    public String objectToJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public String listVehicleDtoToJson(List<?> objects) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<String> jsonList = new ArrayList<>();
            for (var object : objects) {
                String json = objectMapper.writeValueAsString(object);
                jsonList.add(json);
            }
            return "[" + String.join(",", jsonList) + "]";
        } catch (JsonProcessingException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
