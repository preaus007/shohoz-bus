package com.touhed.app.bus.request;

import com.touhed.app.bus.BusType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class AddBusRequest {
    @NotBlank( message = "Bus name is required" )
    private String busName;

    @NotBlank( message = "Bus number is required" )
    private String busNumber;

    @NotBlank( message = "License is required" )
    private String license;

    @Min( value = 1, message = "Total seats must be at least 1" )
    private Integer totalSeats;

    @NotNull( message = "Bus type is required" )
    private BusType busType;

    private Boolean ac;
}
