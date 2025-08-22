package com.jey.bugbox.controller;

import com.jey.bugbox.request.PasswordUpdateRequest;
import com.jey.bugbox.response.UserResponse;
import com.jey.bugbox.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name="User - Service REST API Endpoints")
@RestController
@RequestMapping("/api/users")
public class UserServiceController {

    private final UserService userService;

    public UserServiceController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get current User information")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/info")
    public UserResponse getUserInfo() {
        return userService.getUserInfo();
    }

//    @Operation(summary = "Delete user", description = "Delete current user account")
//    @ResponseStatus(HttpStatus.OK)
//    @DeleteMapping
//    public void deleteUser() {
//        userService.deleteUser();
//    }

    @Operation(summary = "Password update")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/password")
     public void passwordUpdate(@Valid @RequestBody PasswordUpdateRequest passwordUpdateRequest)
            throws Exception {
        userService.updatePassword(passwordUpdateRequest);
    }
}












