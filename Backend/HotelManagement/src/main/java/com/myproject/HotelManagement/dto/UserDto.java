package com.myproject.HotelManagement.dto;

import com.myproject.HotelManagement.enums.UserRole;
import lombok.Data;


@Data
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private UserRole userRole;
}
