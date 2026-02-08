package com.touhed.app.bus.request;

import com.touhed.app.bus.BusType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateBusRequest {
    private String busName;
    private String busNumber;
    private String license;
    private Integer totalSeats;
    private BusType busType;
    private Boolean ac;
}
