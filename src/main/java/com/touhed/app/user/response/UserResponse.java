package com.touhed.app.user.response;

import com.touhed.app.user.model.User;
import com.touhed.app.util.responses.EnumResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private EnumResponse role;
    private EnumResponse bloodGroup;
    private EnumResponse gender;
    private EnumResponse status;

    public UserResponse( User user ) {
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.address = user.getAddress();
        this.status = user.getStatus() != null ?
                new EnumResponse( user.getStatus().getValue(), user.getStatus().name() ) : null;
        this.role = user.getRole() != null ?
                new EnumResponse( user.getRole().getValue(), user.getRole().name() ) : null;
        this.bloodGroup = user.getBloodGroup() != null ?
                new EnumResponse( user.getBloodGroup().getValue() , user.getBloodGroup().name() ) : null;
        this.gender = user.getGender() != null ?
                new EnumResponse( user.getGender().getValue() , user.getGender().name() ) : null;
    }
}
