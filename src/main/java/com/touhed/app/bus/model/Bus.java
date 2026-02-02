package com.touhed.app.bus.model;

import com.touhed.app.bus.BusType;
import com.touhed.app.bus.request.AddBusRequest;
import com.touhed.app.util.audit.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "buses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bus extends AuditableEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotBlank( message = "Bus name is required" )
    @Column( name = "bus_name", nullable = false, length = 100 )
    private String busName;

    @NotBlank( message = "Bus number is required" )
    @Column( name = "bus_number", nullable = false, unique = true, length = 50 )
    private String busNumber;

    @NotBlank( message = "License is required" )
    @Column( nullable = false, unique = true, length = 50 )
    private String license;

    @Min( value = 1, message = "Total seats must be at least 1" )
    @Column( name = "total_seats", nullable = false )
    private Integer totalSeats;

    @Enumerated( EnumType.STRING )
    @Column( name = "bus_type", length = 30 )
    private BusType busType;

    @Column( name = "ac", nullable = false )
    private Boolean ac = false;

    @Column( name = "bus_image", length = 255 )
    private String busImage;

    public Bus( AddBusRequest request) {
        this.busName = request.getBusName();
        this.busNumber = request.getBusNumber();
        this.license = request.getLicense();
        this.totalSeats = request.getTotalSeats();
        this.busType = request.getBusType();
        this.ac = request.getAc();
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || Hibernate.getClass( this ) != Hibernate.getClass( o ) ) return false;
        Bus bus = ( Bus ) o;
        return id != null && Objects.equals( id, bus.id );
    }

    @Override
    public int hashCode() {
        if ( this.id == null )
            return System.identityHashCode( this );
        return Objects.hash( this.id );
    }
}
