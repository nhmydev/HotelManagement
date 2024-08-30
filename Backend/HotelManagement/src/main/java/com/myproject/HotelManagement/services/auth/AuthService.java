package com.myproject.HotelManagement.services.auth;

import com.myproject.HotelManagement.dto.SignupRequest;
import com.myproject.HotelManagement.dto.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);
}
