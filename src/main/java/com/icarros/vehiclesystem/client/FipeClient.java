package com.icarros.vehiclesystem.client;

import com.icarros.vehiclesystem.model.FipeDto;
import com.icarros.vehiclesystem.model.Vehicle;
import com.icarros.vehiclesystem.model.VehicleDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "apifipe", url = "https://parallelum.com.br/fipe/api/v2")
@EnableCaching
public interface FipeClient {

    @GetMapping(value = "/cars/{fipeCode}/years/{year}")
    @Cacheable(value = "VehicleCache")
    Vehicle getCarByFipeCodeAndYear(
            @PathVariable String fipeCode,
            @PathVariable String year);

    @GetMapping(value = "/cars/brands")
    @Cacheable(value = "carBrandCache")
    List<VehicleDto> getCarsBrands();

    @GetMapping(value = "/cars/brands/{brandId}/models")
    @Cacheable(value = "carModelCache")
    List<VehicleDto> getCarsModelByBrand(@PathVariable String brandId);

    @GetMapping(value = "/cars/brands/{brandId}/models/{modelId}/years")
    @Cacheable(value = "carYearCache")
    List<VehicleDto> getCarsYearsByModel(@PathVariable String brandId, @PathVariable String modelId);

    @GetMapping("/cars/brands/{brandId}/models/{modelId}/years/{yearId}")
    @Cacheable(value = "fipeInfoCache")
    FipeDto getCarFipeInfo(@PathVariable String brandId, @PathVariable String modelId, @PathVariable String yearId);
}