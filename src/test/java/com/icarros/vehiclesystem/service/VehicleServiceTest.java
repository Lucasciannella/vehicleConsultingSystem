package com.icarros.vehiclesystem.service;

import com.icarros.vehiclesystem.builders.VehicleBuilder;
import com.icarros.vehiclesystem.client.FipeClient;
import com.icarros.vehiclesystem.infra.FileHandler;
import com.icarros.vehiclesystem.model.Vehicle;
import com.icarros.vehiclesystem.utils.ObjectConversor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class VehicleServiceTest {
    private VehicleService vehicleService;
    private FipeClient fipeClient;
    private FileHandler amazonFileHandler;
    String fipeCode;
    String year;
    private ObjectConversor objectConversor;

    @BeforeEach
    void setUp() {
        fipeClient = Mockito.mock(FipeClient.class);
        amazonFileHandler = Mockito.mock(FileHandler.class);
        objectConversor = Mockito.mock(ObjectConversor.class);
        vehicleService = new VehicleService(fipeClient, amazonFileHandler, objectConversor);
        fipeCode = "004278-1";
        year = "2005-1";
        var vehicle = VehicleBuilder.aVehicle().withPrice("R$3000,00").withModel("HB20").withBrand("hyundai").build();
        Mockito.when(fipeClient.getCarByFipeCodeAndYear("12414", "2003-5")).thenReturn(vehicle);
    }

    @Test
    void must_call_the_method_from_feing_once() {
        this.vehicleService.getCarByFipeCodeAndYear(Mockito.any(), Mockito.any());

        Mockito.verify(fipeClient, Mockito.times(1)).getCarByFipeCodeAndYear(Mockito.any(), Mockito.any());
    }

    @Test
    void must_call_the_method_from_amazon_once() {
        this.vehicleService.getCarByFipeCodeAndYear(Mockito.any(), Mockito.any());

        Mockito.verify(amazonFileHandler, Mockito.times(1)).save(Mockito.any());
    }
}