package com.icarros.vehiclesystem.client;

import com.icarros.vehiclesystem.model.Vehicle;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "apifipe", url = "https://parallelum.com.br")
@EnableCaching
public interface FipeClient {

    @GetMapping(value = "/fipe/api/v2/{vehicleType}/{fipeCode}/years/{year}")
    @Cacheable(value = "VehicleCache")
    Vehicle getVehicleByFipeCodeAndYear(@PathVariable String vehicleType,
                                        @PathVariable String fipeCode,
                                        @PathVariable String year);
}