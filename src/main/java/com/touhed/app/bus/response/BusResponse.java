package com.touhed.app.bus.response;

import com.touhed.app.bus.model.Bus;
import com.touhed.app.util.responses.EnumResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BusResponse {
    private Long id;
    private String busName;
    private String busNumber;
    private String license;
    private Integer totalSeats;
    private EnumResponse busType;
    private Boolean ac;

    public BusResponse( Bus bus ) {
        this.id = bus.getId();
        this.busName = bus.getBusName();
        this.busNumber = bus.getBusNumber();
        this.license = bus.getLicense();
        this.totalSeats = bus.getTotalSeats();
        this.busType = bus.getBusType() != null ?
                new EnumResponse( bus.getBusType().name(), bus.getBusType().getValue() ) : null;
        this.ac = bus.getAc();
    }
}
