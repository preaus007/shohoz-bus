package com.touhed.app.user.service;

import com.touhed.app.user.model.User;
import com.touhed.app.user.repository.UserRepository;
import com.touhed.app.user.request.AddUserRequest;
import com.touhed.app.user.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor( onConstructor_ = {@Autowired} )
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse create( AddUserRequest request ) {

        if( userRepository.existsByEmail( request.getEmail() ) ) {
            throw new RuntimeException( "Email already exists" );
        }

        request.setPassword( passwordEncoder.encode( request.getPassword() ) );
        User user = new User( request );
        User savedUser = userRepository.save(user);

        return new UserResponse( savedUser );
    }
}
