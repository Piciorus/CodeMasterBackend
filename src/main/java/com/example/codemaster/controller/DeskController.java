package com.example.codemaster.controller;

import com.example.codemaster.model.DTO.AIDeskRequestDTO;
import com.example.codemaster.model.DTO.LocalDateDTO;
import com.example.codemaster.model.Desk;
import com.example.codemaster.service.BookingDeskService;
import com.example.codemaster.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/desk")
public class DeskController {
    @Autowired
    private DeskService deskService;

    @Autowired
    private BookingDeskService bookingDeskService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/desks/left")
    public ResponseEntity<List<Desk>> getDesksInRange() {
        List<Desk> desks = deskService.getDesksByLeft();
        return new ResponseEntity<>(desks, HttpStatus.OK);
    }

//    @PostMapping(value = "/desk-prediction/{id}")
//    public String getProductList(@RequestBody LocalDateDTO date, @PathVariable String id) {
//        HttpHeaders headers = new HttpHeaders();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//
//        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
//
//        // Convert LocalDate to LocalDateTime with time set to 07:00:00
//        LocalDateTime startDate = date.getDate().atTime(7, 0, 0);
//
//        // Convert LocalDate to LocalDateTime with time set to 19:00:00
//        LocalDateTime endDate = date.getDate().atTime(19, 0, 0);
//
//        AIDeskRequestDTO aiDeskRequestDTO = AIDeskRequestDTO.builder()
//                .occupancy_rate((float) bookingDeskService.getAllBookingDesksByDate(id, startDate, endDate).size() / 9)
//                .first_part_of_week((date.getDate().getDayOfWeek() == DayOfWeek.MONDAY ||
//                        date.getDate().getDayOfWeek() == DayOfWeek.TUESDAY ||
//                        date.getDate().getDayOfWeek() == DayOfWeek.WEDNESDAY) ? 1 : 0)
//                .second_part_of_week((date.getDate().getDayOfWeek() == DayOfWeek.THURSDAY
//                        || date.getDate().getDayOfWeek() == DayOfWeek.FRIDAY) ? 1 : 0)
//                .first_part_of_month(date.getDate().getDayOfMonth() <= 15 ? 1 : 0)
//                .second_part_of_month(date.getDate().getDayOfMonth() > 15 ? 1 : 0)
//                .build();
//        HttpEntity<AIDeskRequestDTO> entity = new HttpEntity<>(aiDeskRequestDTO, headers);
//
//        return restTemplate.exchange("http://localhost:5000/desk-prediction", HttpMethod.POST, entity, String.class).getBody();
//    }

}
