package com.icarros.vehiclesystem.service;

import com.icarros.vehiclesystem.client.FipeClient;
import com.icarros.vehiclesystem.infra.FileHandler;
import com.icarros.vehiclesystem.model.FipeDto;
import com.icarros.vehiclesystem.model.Vehicle;
import com.icarros.vehiclesystem.model.VehicleDto;
import com.icarros.vehiclesystem.utils.ObjectConversor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final FipeClient fipeClient;
    private final FileHandler amazonFileHandler;
    private final ObjectConversor objectConversor;

    @Autowired
    public VehicleService(FipeClient fipeClient, FileHandler amazonFileHandler, ObjectConversor objectConversor) {
        this.fipeClient = fipeClient;
        this.amazonFileHandler = amazonFileHandler;
        this.objectConversor = objectConversor;
    }

    public Vehicle getCar(String fipeCode, String year) {
        var vehicle = fipeClient.getCarByFipeCodeAndYear(fipeCode, year);
        amazonFileHandler.save(objectConversor.objectToJson(vehicle));
        return vehicle;
    }

    public List<VehicleDto> getCarsBrands() {
        var brands = fipeClient.getCarsBrands();
        amazonFileHandler.save(objectConversor.listVehicleDtoToJson(brands));
        return brands;
    }

    public List<VehicleDto> getCarsModels(String brandId) {
        var models = fipeClient.getCarsModelByBrand(brandId);
        amazonFileHandler.save(objectConversor.listVehicleDtoToJson(models));
        return models;
    }

    public List<VehicleDto> getCarsYears(String brandId, String modelId) {
        var years = fipeClient.getCarsYearsByModel(brandId, modelId);
        amazonFileHandler.save(objectConversor.listVehicleDtoToJson(years));
        return years;
    }

    public FipeDto getCarFipeInfo(String brandId, String modelId, String yearId) {
        var fipeInfo = fipeClient.getCarFipeInfo(brandId, modelId, yearId);
        amazonFileHandler.save(objectConversor.objectToJson(fipeInfo));
        return fipeInfo;
    }
}