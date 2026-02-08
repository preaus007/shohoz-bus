package com.touhed.app.driver.response;

import com.touhed.app.driver.model.Driver;
import com.touhed.app.user.response.UserResponse;
import com.touhed.app.util.responses.EnumResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DriverResponse {

    private UserResponse driver;
    private String drivingLicense;
    private Integer yearsOfExperience;
    private LocalDate licenseExpiry;
    private String image;
    private EnumResponse status;

    public DriverResponse( Driver driver ) {
        this.drivingLicense = driver.getDrivingLicense();
        this.yearsOfExperience = driver.getYearsOfExperience();
        this.licenseExpiry = driver.getLicenseExpiry();
        this.image = driver.getImage();
        this.status = driver.getStatus() != null ?
                new EnumResponse( driver.getStatus().getValue(), driver.getStatus().name() ) : null;
    }

    public DriverResponse( Driver driver, UserResponse user ) {
        this( driver );
        this.driver = user;
    }
}