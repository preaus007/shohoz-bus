package com.touhed.app.driver;

import lombok.Getter;

@Getter
public enum DriverStatus {
    ACTIVE( "Active" ),
    INACTIVE( "Inactive" ),
    SUSPENDED( "Suspended" );

    private final String value;

    DriverStatus(String value ) {
        this.value = value;
    }
}
