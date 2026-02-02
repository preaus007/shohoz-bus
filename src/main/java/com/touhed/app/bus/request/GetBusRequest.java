package com.touhed.app.bus.request;

import com.touhed.app.bus.BusType;
import lombok.Data;

@Data
public class GetBusRequest {
    private String busName;
    private String license;
    private BusType busType;
    private Boolean ac;
}
