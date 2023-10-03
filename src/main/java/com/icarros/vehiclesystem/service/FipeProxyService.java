package com.icarros.vehiclesystem.service;

import com.icarros.vehiclesystem.model.Vehicle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "apifipe", url = "https://parallelum.com.br")
public interface FipeProxyService {

    @GetMapping(value = "/fipe/api/v2/{vehicleType}/{fipeCode}/years/{year}")
    Vehicle getVehicleByFipeCodeAndYear(@PathVariable String vehicleType,
                                        @PathVariable String fipeCode,
                                        @PathVariable String year);
}