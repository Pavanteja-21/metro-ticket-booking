package com.pavan.metro_ticket_booking.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"source_station_id", "destination_station_id"})
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Fare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "source_station_id")
    private Station sourceStation;

    @ManyToOne
    @JoinColumn(name = "destination_station_id")
    private Station destinationStation;
}
