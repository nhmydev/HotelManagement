package com.myproject.HotelManagement.controller.auth;

import com.myproject.HotelManagement.dto.AuthenRequest;
import com.myproject.HotelManagement.dto.AuthenResponse;
import com.myproject.HotelManagement.dto.SignupRequest;
import com.myproject.HotelManagement.dto.UserDto;
import com.myproject.HotelManagement.entity.User;
import com.myproject.HotelManagement.repository.UserRepository;
import com.myproject.HotelManagement.services.auth.AuthService;
import com.myproject.HotelManagement.services.jwt.UserService;
import com.myproject.HotelManagement.uitls.JWTUtil;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {
    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final JWTUtil jwtUtil;

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest request){
        try {
            UserDto createdUser = authService.createUser(request);
            return new ResponseEntity<>(createdUser,HttpStatus.OK);
        }catch (EntityExistsException exception){
            return new ResponseEntity<>("Nguoi dung da ton tai",HttpStatus.NOT_ACCEPTABLE);
        }catch (Exception e){
            return new ResponseEntity<>("Tao tai khoan that bai",HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public AuthenResponse createAuthenToken(@RequestBody AuthenRequest authenRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenRequest.getEmail(),authenRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Sai mat khau hoac tai khoan");
        }
        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenRequest.getEmail());
        Optional<User> optionalUser = userRepository.findByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        AuthenResponse authenResponse = new AuthenResponse();
        if(optionalUser.isPresent()){
            authenResponse.setJwt(jwt);
            authenResponse.setUserRole(optionalUser.get().getUserRole());
            authenResponse.setUserid(optionalUser.get().getId());
        }
        return authenResponse;
    }
}
