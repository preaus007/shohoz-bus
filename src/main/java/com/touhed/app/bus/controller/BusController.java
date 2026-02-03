package com.touhed.app.bus.controller;

import com.touhed.app.bus.request.AddBusRequest;
import com.touhed.app.bus.request.GetBusRequest;
import com.touhed.app.bus.request.UpdateBusRequest;
import com.touhed.app.bus.response.BusResponse;
import com.touhed.app.bus.response.FilterOptionResponse;
import com.touhed.app.bus.services.BusService;
import com.touhed.app.util.responses.ApiResponse;
import com.touhed.app.util.responses.PaginatedApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/buses" )
@RequiredArgsConstructor( onConstructor_ = {@Autowired} )
public class BusController {
    private final BusService busService;

    @GetMapping
    public PaginatedApiResponse<List<BusResponse>> findAll( GetBusRequest request, Pageable pageable ) {
        return busService.findAll( request, pageable );
    }

    @PostMapping
    public ApiResponse<BusResponse> create( @Valid @RequestBody AddBusRequest request ) {
        BusResponse response = busService.create( request );
        return new ApiResponse<>(
                "New Bus registered successfully",
                HttpStatus.CREATED.value(),
                response
        );
    }

    @GetMapping( "/{id}" )
    public ApiResponse<BusResponse> getById( @PathVariable Long id ) {
        BusResponse response = busService.getById( id );
        return new ApiResponse<>(
                "The bus fetched successfully",
                HttpStatus.CREATED.value(),
                response
        );
    }

    @PatchMapping( "/{id}" )
    public ApiResponse<BusResponse> update(@PathVariable Long id, @RequestBody UpdateBusRequest request ) {
        BusResponse response = busService.update( id, request );
        return new ApiResponse<>(
                "Updated successfully",
                HttpStatus.CREATED.value(),
                response
        );
    }

    @DeleteMapping( "/{id}" )
    public ApiResponse<?> delete( @PathVariable Long id ) {
        busService.delete( id );
        return new ApiResponse<>( "Deleted successfully", HttpStatus.NO_CONTENT.value() );
    }

    @GetMapping( "/filter-options" )
    public ApiResponse<FilterOptionResponse> getFilterOptions() {
        FilterOptionResponse options = busService.getFilterOptions();
        return new ApiResponse<>( "Form options", HttpStatus.OK.value(), options );
    }
}
