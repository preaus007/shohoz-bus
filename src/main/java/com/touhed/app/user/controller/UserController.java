package com.touhed.app.user.controller;

import com.touhed.app.user.request.AddUserRequest;
import com.touhed.app.user.response.UserResponse;
import com.touhed.app.user.service.UserService;
import com.touhed.app.util.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/users" )
@RequiredArgsConstructor( onConstructor_ = {@Autowired} )
public class UserController {
    private final UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> create( @RequestBody AddUserRequest request ) {
        UserResponse response = userService.create(request);
        return new ApiResponse<>(
                "Created successfully",
                HttpStatus.CREATED.value(),
                response
        );
    }
}
