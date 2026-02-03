package com.touhed.app.user;

import lombok.Getter;

@Getter
public enum UserRole {
    CUSTOMER( "Customer" ),
    DRIVER( "Driver" ),
    ADMIN( "Admin" );

    private final String value;

    UserRole( String value ) {
        this.value = value;
    }
}
