package com.example.codemaster.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "desk")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Desk {
    @Id
    private String id;
    private Long capacity;

    @OneToMany(mappedBy = "desk")
    private Set<BookingDesk> userDesks = new HashSet<>();
}
