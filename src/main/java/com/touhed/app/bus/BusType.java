package com.touhed.app.bus;

import lombok.Getter;

@Getter
public enum BusType {
    SEATER( "Seater" ),
    SLEEPER( "Sleeper" ),
    BOTH( "Both" );

    private final String value;

    BusType( String value ) {
        this.value = value;
    }
}
