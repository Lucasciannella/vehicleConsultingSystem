package com.icarros.vehiclesystem.controller;

import com.icarros.vehiclesystem.builders.VehicleBuilder;
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

//TODO:AJUSTAR TESTES QUANDO A API_FIPE VOLTAR A FUNCIONAR
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

        var vehicle = VehicleBuilder.aVehicle().build();
        Mockito.when(vehicleService.getCarByFipeCodeAndYear("004278-1", "2005-1")).thenReturn(vehicle);
    }

    @Test
    void testGetCarByFipeCodeAndYear() throws Exception {
        mockMvc.perform(get("/vehicle-api/v1/vehicle/fipe/004278-1/years/2005-1"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetCarsBrands() throws Exception {
        mockMvc.perform(get("/cars/brands"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetCarsModelByBrand() throws Exception {
        mockMvc.perform(get("/cars/brands/{brandId}/models"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetCarYearsByModel() throws Exception {
        mockMvc.perform(get("/car/brand/{brandId}/model/{modelId}/years"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetCarFipeInfoBy() throws Exception {
        mockMvc.perform(get("/car/brands/{brandId}/models/{modelId}/years/{yearId}"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}