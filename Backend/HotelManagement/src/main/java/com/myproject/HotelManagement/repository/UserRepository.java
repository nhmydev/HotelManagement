package com.myproject.HotelManagement.repository;

import com.myproject.HotelManagement.entity.User;
import com.myproject.HotelManagement.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUserRole(UserRole userRole);
}
