package com.jey.bugbox.controller;

import com.jey.bugbox.request.AuthenticationRequest;
import com.jey.bugbox.request.RegisterRequest;
import com.jey.bugbox.response.AuthenticationResponse;
import com.jey.bugbox.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication REST API Endpoints")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "Register As New User")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register/user")
    public String registerInstructor(@RequestBody RegisterRequest request) throws Exception {
        authenticationService.register(request, "ROLE_USER");
        return "User registered successfully!";
    }

    @Operation(summary = "Register As New Admin")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register/admin")
    public String registerAdmin(@RequestBody RegisterRequest request) throws Exception {
        authenticationService.register(request, "ROLE_ADMIN");
        return "User registered successfully!";
    }

    @Operation(summary = "Login a user")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public AuthenticationResponse login(@Valid @RequestBody AuthenticationRequest authRequest) {
        return authenticationService.login(authRequest);
    }
}











