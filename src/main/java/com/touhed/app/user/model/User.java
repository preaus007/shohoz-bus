package com.touhed.app.user.model;

import com.touhed.app.booking.model.Booking;
import com.touhed.app.cancellation.model.Cancellation;
import com.touhed.app.driver.model.Driver;
import com.touhed.app.fullbusrequest.model.FullBusRequest;
import com.touhed.app.review.model.Review;
import com.touhed.app.user.BloodGroup;
import com.touhed.app.user.Gender;
import com.touhed.app.user.UserRole;
import com.touhed.app.user.UserStatus;
import com.touhed.app.user.request.AddUserRequest;
import com.touhed.app.util.audit.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends AuditableEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotBlank( message = "Full name is required" )
    @Column( name = "full_name", nullable = false, length = 100 )
    private String fullName;

    @NotBlank( message = "Email is required" )
    @Email( message = "Email should be valid" )
    @Column( nullable = false, unique = true, length = 100 )
    private String email;

    @Pattern( regexp = "^\\+?[0-9]{10,15}$", message = "Phone number should be valid" )
    @Column( length = 20 )
    private String phone;

    @NotBlank( message = "Password is required" )
    @Column( nullable = false )
    private String password;

    @Column( nullable = false )
    private String address;

    @Enumerated( EnumType.STRING )
    @Column( name = "gender" )
    private Gender gender;

    @Enumerated( EnumType.STRING )
    @Column( name = "blood_group" )
    private BloodGroup bloodGroup;

    @Enumerated( EnumType.STRING )
    @Column( nullable = false, length = 20 )
    private UserRole role;

    @Enumerated( EnumType.STRING )
    @Column( nullable = false, length = 20 )
    private UserStatus status = UserStatus.ACTIVE;

    @Column( name = "refresh_token" )
    private String refreshToken;

    public User( AddUserRequest request ) {
        this.fullName = request.getFullName();
        this.email = request.getEmail();
        this.phone = request.getPhone();
        this.role = request.getRole();
        this.gender = request.getGender();
        this.bloodGroup = request.getBloodGroup();
        this.address = request.getAddress();
        this.role = request.getRole();
        this.password = request.getPassword();
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || Hibernate.getClass( this ) != Hibernate.getClass( o ) ) return false;
        User user = ( User ) o;
        return id != null && Objects.equals( id, user.id );
    }

    @Override
    public int hashCode() {
        if ( this.id == null )
            return System.identityHashCode( this );
        return Objects.hash( this.id );
    }
}
