package com.myproject.HotelManagement.services.auth;


import com.myproject.HotelManagement.dto.SignupRequest;
import com.myproject.HotelManagement.dto.UserDto;
import com.myproject.HotelManagement.entity.User;
import com.myproject.HotelManagement.enums.UserRole;
import com.myproject.HotelManagement.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

//    Create Admin
//    @PostConstruct
//    public void createAdminAccount(){
//        Optional<User> adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
//        if(adminAccount.isEmpty()){
//            User user = new User();
//            user.setEmail("admin@gmail.com");
//            user.setName("admin");
//            user.setUserRole(UserRole.ADMIN);
//            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
//            userRepository.save(user);
//            System.out.println("Tao tai khoan thanh cong");
//        }else {
//            System.out.println("Tai khoan da ton tai");
//        }
//    }

    public UserDto createUser(SignupRequest signupRequest) {
        if (userRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
            throw new EntityExistsException("Tai khoan da ton tai" + signupRequest.getEmail());
        }
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setUserRole(UserRole.CUSTOMER);
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        User createdUser = userRepository.save(user);
        return createdUser.getUserDto();
    }
}
