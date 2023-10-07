package com.icarros.vehiclesystem.controller;

import com.icarros.vehiclesystem.model.FipeDto;
import com.icarros.vehiclesystem.model.Vehicle;
import com.icarros.vehiclesystem.model.VehicleDto;
import com.icarros.vehiclesystem.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vehicle-api/v1")
@EnableCaching
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/car/fipe/{fipeCode}/years/{year}")
    public Vehicle getCarByFipeCodeAndYear(@PathVariable String fipeCode, @PathVariable String year) {
        return vehicleService.getCarByFipeCodeAndYear(fipeCode, year);
    }

    @GetMapping("/cars/brands")
    public List<VehicleDto> getCarsBrands() {
        return vehicleService.getCarsBrands();
    }

    @GetMapping("/cars/brands/{brandId}/models")
    public List<VehicleDto> getCarsModelByBrands(@PathVariable String brandId) {
        return vehicleService.getCarsModelByBrands(brandId);
    }

    @GetMapping("/car/brand/{brandId}/model/{modelId}/years")
    public List<VehicleDto> getCarsBrands(@PathVariable String brandId, @PathVariable String modelId) {
        return vehicleService.getCarsYearByModel(brandId, modelId);
    }

    @GetMapping("/car/brands/{brandId}/models/{modelId}/years/{yearId}")
    public FipeDto getCarFipeInfoBy(@PathVariable String brandId, @PathVariable String modelId, @PathVariable String yearId) {
        return vehicleService.getCarFipeInfoBy(brandId, modelId, yearId);
    }
}