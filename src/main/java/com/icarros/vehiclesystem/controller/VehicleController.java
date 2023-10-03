package com.icarros.vehiclesystem.controller;

import com.icarros.vehiclesystem.model.Vehicle;
import com.icarros.vehiclesystem.service.FipeProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("vehicle-api/v1")
public class VehicleController {

    private final FipeProxyService fipeProxyService;

    @Autowired
    public VehicleController(FipeProxyService fipeProxyService) {
        this.fipeProxyService = fipeProxyService;
    }

    @GetMapping("/vehicle/{vehicleType}/fipe/{fipeCode}/years/{year}")
    public Vehicle getVehicleByTypeFipeCodeAndYear(@PathVariable String vehicleType,
                                                   @PathVariable String fipeCode,
                                                   @PathVariable String year) {
        return fipeProxyService.getVehicleByFipeCodeAndYear(vehicleType, fipeCode, year);
    }
}