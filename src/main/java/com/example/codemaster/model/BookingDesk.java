package com.example.codemaster.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking_desk")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDesk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "desk_id")
    private Desk desk;

    private Long available;

    private LocalDateTime bookingTime;
}
