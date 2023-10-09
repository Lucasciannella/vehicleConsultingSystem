package com.icarros.vehiclesystem.controller;

import com.icarros.vehiclesystem.builders.VehicleBuilder;
import com.icarros.vehiclesystem.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//TODO:AJUSTAR TESTES QUANDO A API_FIPE VOLTAR A FUNCIONAR
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Disabled
class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    VehicleService vehicleService;

    @BeforeEach
    void setUp() {

        var vehicle = VehicleBuilder.aVehicle().build();
        Mockito.when(vehicleService.getCar("004278-1", "2005-1")).thenReturn(vehicle);
    }

    @Test
    void testGetCarByFipeCodeAndYear() throws Exception {
        mockMvc.perform(get("/vehicle-api/v1/car/fipe/006004-6/years/1997-1"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetCarsBrands() throws Exception {
        mockMvc.perform(get("/vehicle-api/v1/cars/brands"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetCarsModelByBrand() throws Exception {
        mockMvc.perform(get("/vehicle-api/v1/cars/brands/3/models"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetCarYearsByModel() throws Exception {
        mockMvc.perform(get("/vehicle-api/v1/car/brand/3/model/11/years"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetCarFipeInfoBy() throws Exception {
        mockMvc.perform(get("/vehicle-api/v1/car/brands/3/models/11/years/1997-1"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}