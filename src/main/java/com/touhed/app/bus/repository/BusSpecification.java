package com.touhed.app.bus.repository;

import com.touhed.app.bus.BusType;
import com.touhed.app.bus.model.Bus;
import org.springframework.data.jpa.domain.Specification;

public class BusSpecification {
    public static Specification<Bus> filterByBusName( String busName ) {

        return( root, query, cb ) -> {
            if( busName == null || busName.isBlank() )
                return cb.conjunction();

            return cb.like( cb.lower( root.get( "name" ) ), "%" + busName.toLowerCase() + "%" );
        };
    }

    public static Specification<Bus> filterByLicense( String license ) {
        return( root, query, cb ) -> {
            if( license == null || license.isBlank() )
                return cb.conjunction();

            return cb.like( cb.lower( root.get( "name" ) ), "%" + license.toLowerCase() + "%" );
        };
    }

    public static Specification<Bus> filterByBusType( BusType busType ) {
        return( root, query, cb ) -> {
            if( busType == null )
                return cb.conjunction();

            return cb.equal( root.get( "busType" ), busType );
        };
    }

    public static Specification<Bus> filterByAc( Boolean ac ) {
        return( root, query, cb ) -> {
            if( ac == null )
                return cb.conjunction();

            return cb.equal( root.get( "ac" ), ac );
        };
    }
}
