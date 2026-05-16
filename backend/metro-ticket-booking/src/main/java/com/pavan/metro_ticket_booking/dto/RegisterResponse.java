package com.pavan.metro_ticket_booking.dto;

import com.pavan.metro_ticket_booking.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {

    private String id;

    private String name;

    private String email;

    private Role role;

    private LocalDateTime createdAt;
}
