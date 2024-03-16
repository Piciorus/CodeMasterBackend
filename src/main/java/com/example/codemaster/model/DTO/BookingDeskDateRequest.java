package com.example.codemaster.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingDeskDateRequest {
    private String start_date;
    private String end_date;
}
