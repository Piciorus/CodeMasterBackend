package com.example.codemaster.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AddBookingDeskDTO {
    private String deskId;
    private String username;
    private String start_date;
    private String end_date;
}
