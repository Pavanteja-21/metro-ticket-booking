package com.pavan.metro_ticket_booking.service;

import com.pavan.metro_ticket_booking.dto.LoginRequest;
import com.pavan.metro_ticket_booking.dto.LoginResponse;
import com.pavan.metro_ticket_booking.dto.RegisterRequest;
import com.pavan.metro_ticket_booking.dto.RegisterResponse;
import com.pavan.metro_ticket_booking.entity.User;
import com.pavan.metro_ticket_booking.exceptions.InvalidCredentialsException;
import com.pavan.metro_ticket_booking.exceptions.UserAlreadyExistsException;
import com.pavan.metro_ticket_booking.repository.UserRepository;
import com.pavan.metro_ticket_booking.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public  RegisterResponse register(RegisterRequest request) throws UserAlreadyExistsException {
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User already registered.");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        User savedUser = userRepository.save(user);

        return RegisterResponse.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .name(savedUser.getName())
                .role(savedUser.getRole())
                .createdAt(savedUser.getCreatedAt())
                .build();

    }

    public LoginResponse login(LoginRequest loginRequest) {
       User user = userRepository.findByEmail(loginRequest.getEmail())
               .orElseThrow(() -> new UsernameNotFoundException("User not found with email"));

       if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
           throw new InvalidCredentialsException("Invalid Credentials");
       }

       String token = jwtService.generateToken(user.getName());

       return new LoginResponse(token,  response(user));
    }

    private RegisterResponse response (User user) {
        RegisterResponse response = new RegisterResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }
}
