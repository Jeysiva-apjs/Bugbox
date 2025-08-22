package com.jey.bugbox.service;

import com.jey.bugbox.request.AuthenticationRequest;
import com.jey.bugbox.request.RegisterRequest;
import com.jey.bugbox.response.AuthenticationResponse;

public interface AuthenticationService {
    void register(RegisterRequest input, String role) throws Exception;
    AuthenticationResponse login(AuthenticationRequest request);
}
