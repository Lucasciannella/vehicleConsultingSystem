package com.icarros.vehiclesystem.controller;

import com.icarros.vehiclesystem.model.Vehicle;
import com.icarros.vehiclesystem.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    VehicleService vehicleService;

    @BeforeEach
    void setUp() {
        Vehicle vehicle = new Vehicle("carro", "teste", "teste");
        Mockito.when(vehicleService.getCarByFipeCodeAndYear("004278-1", "2005-1")).thenReturn(vehicle);
    }

    @Test
    void testGetCarByFipeCodeAndYear() throws Exception {
        mockMvc.perform(get("/vehicle-api/v1/vehicle/fipe/004278-1/years/2005-1"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}