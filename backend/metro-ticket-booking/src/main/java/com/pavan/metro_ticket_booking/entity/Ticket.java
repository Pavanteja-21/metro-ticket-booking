package com.pavan.metro_ticket_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pavan.metro_ticket_booking.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer fare;

    @CreationTimestamp
    private LocalDateTime bookingTime;

    private LocalDate travelDate;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "source_station_id", nullable = false)
    @JsonIgnore
    private Station sourceStation;

    @ManyToOne
    @JoinColumn(name = "destination_station_id", nullable = false)
    @JsonIgnore
    private Station destinationStation;

}
