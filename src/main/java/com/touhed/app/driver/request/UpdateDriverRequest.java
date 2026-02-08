package com.touhed.app.driver.request;

import com.touhed.app.driver.DriverStatus;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateDriverRequest {

    @Min(value = 0, message = "Years of experience cannot be negative")
    private Integer yearsOfExperience;
    private LocalDate licenseExpiry;
    private String image;
    private DriverStatus status;
}