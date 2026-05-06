package com.pavan.metro_ticket_booking.entity;

import com.pavan.metro_ticket_booking.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;
}
