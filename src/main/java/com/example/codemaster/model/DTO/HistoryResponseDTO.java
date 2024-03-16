package com.example.codemaster.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HistoryResponseDTO {
    private String username;
    private String deskBooked;
    private String startBookingTime;
    private String endBookingTime;
}
