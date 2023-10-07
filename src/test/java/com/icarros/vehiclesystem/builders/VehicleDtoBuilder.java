package com.icarros.vehiclesystem.builders;

import com.icarros.vehiclesystem.model.VehicleDto;

public final class VehicleDtoBuilder {
    private String name;
    private String code;

    private VehicleDtoBuilder() {
    }

    public static VehicleDtoBuilder aVehicleDto() {
        return new VehicleDtoBuilder();
    }

    public VehicleDtoBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public VehicleDtoBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public VehicleDto build() {
        return new VehicleDto(name, code);
    }
}
