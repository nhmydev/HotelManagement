package com.myproject.HotelManagement.dto;

import com.myproject.HotelManagement.enums.UserRole;
import lombok.Data;

@Data
public class AuthenResponse {
    private String jwt;
    private Long userid;
    private UserRole userRole;
}
