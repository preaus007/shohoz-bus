package com.touhed.app.bus.services;

import com.touhed.app.bus.BusType;
import com.touhed.app.bus.model.Bus;
import com.touhed.app.bus.repository.BusRepository;
import com.touhed.app.bus.repository.BusSpecification;
import com.touhed.app.bus.request.AddBusRequest;
import com.touhed.app.bus.request.GetBusRequest;
import com.touhed.app.bus.request.UpdateBusRequest;
import com.touhed.app.bus.response.BusResponse;
import com.touhed.app.bus.response.FilterOptionResponse;
import com.touhed.app.util.exceptions.NotFoundException;
import com.touhed.app.util.responses.EnumResponse;
import com.touhed.app.util.responses.PaginatedApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor( onConstructor_ = {@Autowired} )
public class BusService {
    private final BusRepository busRepository;

    public PaginatedApiResponse<List<BusResponse>> findAll( GetBusRequest request, Pageable pageable ) {
        Specification<Bus> specification = getBusSpecification( request );
        Page<Bus> busPage = busRepository.findAll( specification, pageable );

        List<BusResponse> buses = busPage.getContent()
                .stream()
                .map( BusResponse::new )
                .toList();

        return new PaginatedApiResponse<>(
                buses,
                pageable.getPageNumber(),
                busPage.getTotalPages(),
                busPage.getTotalElements()
        );
    }

    private Specification<Bus> getBusSpecification( GetBusRequest request ) {
        return BusSpecification.filterByBusName( request.getBusName() )
                .and( BusSpecification.filterByLicense( request.getLicense() ) )
                .and( BusSpecification.filterByBusType( request.getBusType() ) )
                .and( BusSpecification.filterByAc( request.getAc() ) );
    }

    public BusResponse create( AddBusRequest request ) {
        Bus bus = new Bus( request );
        bus = busRepository.save( bus );
        return new BusResponse( bus );
    }

    public BusResponse getById( Long id ) {
        Bus bus = busRepository.findByIdNotDeleted( id )
                .orElseThrow( () -> new NotFoundException( "Bus not found with id: ", id ) );

        return new BusResponse( bus );
    }


    public BusResponse update( Long id, UpdateBusRequest request ) {

        Bus bus = busRepository.findByIdNotDeleted( id )
                .orElseThrow( () -> new NotFoundException( "Bus not found with id: ", id ) );

        if( request.getBusName() != null ) bus.setBusName( request.getBusName() );
        if( request.getBusNumber() != null ) bus.setBusNumber( request.getBusNumber() );
        if( request.getLicense() != null ) bus.setLicense( request.getLicense() );
        if( request.getTotalSeats() != null ) bus.setTotalSeats( request.getTotalSeats() );
        if( request.getBusType() != null ) bus.setBusType( request.getBusType() );
        if( request.getAc() != null ) bus.setAc( request.getAc() );

        Bus updatedBus = busRepository.save( bus );
        return new BusResponse( updatedBus );
    }


    public void delete( Long id ) {
        Bus bus = busRepository.findById( id )
                .orElseThrow( () -> new NotFoundException( "Bus not found with id: ", id ) );

        bus.setIsDeleted( true );
        busRepository.save( bus );

    }

    public FilterOptionResponse getFilterOptions() {
        List<EnumResponse> types = Arrays.stream( BusType.values() )
                .map( type -> new EnumResponse( type.name(), type.getValue() ) )
                .toList();

        return new FilterOptionResponse( types );
    }
}
