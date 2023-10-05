package com.icarros.vehiclesystem.controller;

import com.icarros.vehiclesystem.model.Vehicle;
import com.icarros.vehiclesystem.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("vehicle-api/v1")
@EnableCaching
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/vehicle/{vehicleType}/fipe/{fipeCode}/years/{year}")
    public Vehicle getVehicleByTypeFipeCodeAndYear(@PathVariable String vehicleType, @PathVariable String fipeCode, @PathVariable String year) {
        return vehicleService.getVehicleByFipeCodeAndYear(vehicleType, fipeCode, year);
    }
}