package com.pavan.metro_ticket_booking.controller;

import com.pavan.metro_ticket_booking.dto.LoginRequest;
import com.pavan.metro_ticket_booking.dto.LoginResponse;
import com.pavan.metro_ticket_booking.dto.RegisterRequest;
import com.pavan.metro_ticket_booking.dto.RegisterResponse;
import com.pavan.metro_ticket_booking.exceptions.UserAlreadyExistsException;
import com.pavan.metro_ticket_booking.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) throws UserAlreadyExistsException {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }


}
