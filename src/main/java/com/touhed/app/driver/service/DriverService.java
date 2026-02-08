package com.touhed.app.driver.service;

import com.touhed.app.driver.DriverStatus;
import com.touhed.app.driver.model.Driver;
import com.touhed.app.driver.repository.DriverRepository;
import com.touhed.app.driver.request.AddDriverRequest;
import com.touhed.app.driver.request.UpdateDriverRequest;
import com.touhed.app.driver.response.DriverResponse;
import com.touhed.app.user.model.User;
import com.touhed.app.user.repository.UserRepository;
import com.touhed.app.user.response.UserResponse;
import com.touhed.app.util.exceptions.NotFoundException;
import com.touhed.app.util.responses.PaginatedApiResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor( onConstructor_ = {@Autowired} )
public class DriverService {
    private final DriverRepository driverRepository;
    private final UserRepository userRepository;

    public DriverResponse create( AddDriverRequest request ) {

        User user = userRepository.findById( request.getUserId() )
                .orElseThrow( () -> new NotFoundException( "User not found with id: ", request.getUserId() ) );

        Driver driver = new Driver();
        driver.setDrivingLicense( request.getDrivingLicense());
        driver.setYearsOfExperience( request.getYearsOfExperience());
        driver.setLicenseExpiry( request.getLicenseExpiry());
        driver.setImage( request.getImage());
        driver.setUser( user );

        Driver savedDriver = driverRepository.save( driver );

        return new DriverResponse( savedDriver, new UserResponse( user ) );
    }

    public DriverResponse update( Long driverId, UpdateDriverRequest request ) {

        Driver driver = driverRepository.findById( driverId )
                .orElseThrow(() -> new NotFoundException( "Driver not found with id: ", driverId ) );

        if (request.getYearsOfExperience() != null) {
            driver.setYearsOfExperience(request.getYearsOfExperience());
        }
        if (request.getLicenseExpiry() != null) {
            driver.setLicenseExpiry(request.getLicenseExpiry());
        }
        if (request.getImage() != null) {
            driver.setImage(request.getImage());
        }
        if (request.getStatus() != null) {
            driver.setStatus(request.getStatus());
        }

        Driver savedDriver = driverRepository.save( driver );

        return new DriverResponse( savedDriver );
    }

    public void delete(Long driverId) {

        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        driver.setStatus( DriverStatus.INACTIVE );
        driver.setIsDeleted( true );

        driverRepository.save( driver );
    }

    @Transactional
    public DriverResponse getDriverById( Long driverId ) {

        Driver driver = driverRepository.findById( driverId )
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        return new DriverResponse( driver, new UserResponse( driver.getUser() ) );
    }

    public PaginatedApiResponse<List<DriverResponse>> getAll(Pageable pageable ) {
        Page<Driver> drivers = driverRepository.findAll( pageable );

        List<Long> driversId = drivers.getContent()
                .stream()
                .map( Driver::getId )
                .toList();

        Map<Long, User> userMap = userRepository.findAllById( driversId )
                .stream()
                .collect( Collectors.toMap( User::getId, Function.identity() ) );

        List<DriverResponse> responses = drivers.getContent()
                .stream()
                .map(driver -> {
                    User user = userMap.get( driver.getId() );
                    UserResponse userResponse = new UserResponse( user );
                    return new DriverResponse( driver, userResponse );
        }).toList();

        return new PaginatedApiResponse<>(
                responses,
                pageable.getPageNumber(),
                drivers.getTotalPages(),
                drivers.getTotalElements()
        );
    }
}
