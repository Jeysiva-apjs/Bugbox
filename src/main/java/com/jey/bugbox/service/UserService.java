package com.jey.bugbox.service;

import com.jey.bugbox.request.PasswordUpdateRequest;
import com.jey.bugbox.response.UserResponse;

public interface UserService {
    UserResponse getUserInfo();
    void deleteUser();
    void updatePassword(PasswordUpdateRequest passwordUpdateRequest);
}
