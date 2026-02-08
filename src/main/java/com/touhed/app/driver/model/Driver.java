package com.touhed.app.driver.model;

import com.touhed.app.driver.DriverStatus;
import com.touhed.app.user.model.User;
import com.touhed.app.util.audit.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "drivers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank( message = "Driving license is required" )
    @Column( name = "driving_license", nullable = false, unique = true, length = 50 )
    private String drivingLicense;

    @Min( value = 0, message = "Years of experience cannot be negative" )
    @Column( name = "years_of_experience" )
    private Integer yearsOfExperience;

    @Column( name = "license_expiry" )
    private LocalDate licenseExpiry;

    @Column( length = 255 )
    private String image;

    @Enumerated( EnumType.STRING )
    @Column( nullable = false, length = 20 )
    private DriverStatus status = DriverStatus.ACTIVE;

    @OneToOne
    @JoinColumn( name = "user_id", nullable = false, unique = true )
    private User user;

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || Hibernate.getClass( this ) != Hibernate.getClass( o ) ) return false;
        Driver driver = ( Driver ) o;
        return id != null && Objects.equals( id, driver.id );
    }

    @Override
    public int hashCode() {
        if ( this.id == null )
            return System.identityHashCode( this );
        return Objects.hash( this.id );
    }
}
