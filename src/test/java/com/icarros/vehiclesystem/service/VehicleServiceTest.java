package com.icarros.vehiclesystem.service;

import com.icarros.vehiclesystem.client.FipeClient;
import com.icarros.vehiclesystem.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class VehicleServiceTest {
    private VehicleService vehicleService;
    private FipeClient fipeClient;
    private AmazonService amazonService;
    String fipeCode;
    String year;

    @BeforeEach
    void setUp() {
        fipeClient = Mockito.mock(FipeClient.class);
        amazonService = Mockito.mock(AmazonService.class);
        vehicleService = new VehicleService(fipeClient, amazonService);
        fipeCode = "004278-1";
        year = "2005-1";
        var vehicle = new Vehicle("teste", "teste", "teste");
        Mockito.when(fipeClient.getCarByFipeCodeAndYear("12414", "2003-5")).thenReturn(vehicle);
        Mockito.doNothing().when(amazonService).uploadJsonToAwsS3(vehicle);
    }

    @Test
    void must_call_the_method_from_feing_once() {
        this.vehicleService.getCarByFipeCodeAndYear(Mockito.any(), Mockito.any());

        Mockito.verify(fipeClient, Mockito.times(1)).getCarByFipeCodeAndYear(Mockito.any(), Mockito.any());
    }

    @Test
    void must_call_the_method_from_amazon_once() {
        this.vehicleService.getCarByFipeCodeAndYear(Mockito.any(), Mockito.any());

        Mockito.verify(amazonService, Mockito.times(1)).uploadJsonToAwsS3(Mockito.any());
    }
}