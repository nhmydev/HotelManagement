package com.myproject.HotelManagement.dto;

import lombok.Data;

@Data
public class AuthenRequest {
    private String email;
    private String password;
}
