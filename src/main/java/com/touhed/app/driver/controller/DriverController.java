package com.touhed.app.driver.controller;

import com.touhed.app.driver.request.AddDriverRequest;
import com.touhed.app.driver.request.UpdateDriverRequest;
import com.touhed.app.driver.response.DriverResponse;
import com.touhed.app.driver.service.DriverService;
import com.touhed.app.util.responses.ApiResponse;
import com.touhed.app.util.responses.PaginatedApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping( "/driver" )
@RequiredArgsConstructor( onConstructor_ = {@Autowired} )
public class DriverController {
    private final DriverService driverService;

    @PreAuthorize( "hasRole( 'ADMIN' )" )
    @PostMapping
    public ApiResponse<DriverResponse> createDriver( @Valid @RequestBody AddDriverRequest request ) {

        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        DriverResponse response = driverService.create( request );
        return new ApiResponse<>(
                "New driver registered",
                HttpStatus.CREATED.value(),
                response
        );
    }

    @GetMapping( "/{id}" )
    public ApiResponse<DriverResponse> getDriverById( @PathVariable Long id ) {
        DriverResponse response = driverService.getDriverById(id);
        return new ApiResponse<>(
                "Get the driver",
                HttpStatus.OK.value(),
                response
        );
    }

    @PreAuthorize( "hasRole( 'ADMIN' )" )
    @PatchMapping( "/{id}" )
    public ApiResponse<DriverResponse> updateDriver(
            @PathVariable Long id,
            @Valid @RequestBody UpdateDriverRequest request ) {
        DriverResponse response = driverService.update( id, request );
        return new ApiResponse<>(
                "Driver updated successfully",
                HttpStatus.OK.value(),
                response
        );
    }

    @PreAuthorize( "hasRole( 'ADMIN' )" )
    @DeleteMapping("/{id}")
    public ApiResponse<DriverResponse> deleteDriver( @PathVariable Long id ) {
        driverService.delete( id );
        return new ApiResponse<>(
                "Driver deleted",
                HttpStatus.NO_CONTENT.value()
        );
    }

    @PreAuthorize( "hasRole( 'ADMIN' )" )
    @GetMapping
    public PaginatedApiResponse<List<DriverResponse>> getDrivers(Pageable pageable ) {
       return driverService.getAll( pageable );
    }
}
