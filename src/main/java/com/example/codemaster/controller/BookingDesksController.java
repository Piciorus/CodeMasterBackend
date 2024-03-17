package com.example.codemaster.controller;

import com.example.codemaster.model.BookingDesk;
import com.example.codemaster.model.DTO.BookingDeskDateRequest;
import com.example.codemaster.model.DTO.HistoryResponseDTO;
import com.example.codemaster.service.BookingDeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookingDesksController {

    @Autowired
    private BookingDeskService bookingDeskService;

    @GetMapping("/booking/left")
    public ResponseEntity<List<BookingDesk>> getDesks() {
        List<BookingDesk> desks = bookingDeskService.getBookingDeskByLeft();
        return new ResponseEntity<>(desks, HttpStatus.OK);
    }

    @GetMapping("/booking/top")
    public ResponseEntity<List<BookingDesk>> getBookingDesksByTop() {
        List<BookingDesk> desks = bookingDeskService.getBookingDesksByTop();
        return new ResponseEntity<>(desks, HttpStatus.OK);
    }

    @GetMapping("/booking/top2")
    public ResponseEntity<List<BookingDesk>> getBookingDesksByTop2() {
        List<BookingDesk> desks = bookingDeskService.getBookingDesksByTop2();
        return new ResponseEntity<>(desks, HttpStatus.OK);
    }

    @GetMapping("/booking/top3")
    public ResponseEntity<List<BookingDesk>> getBookingDesksByTop3() {
        List<BookingDesk> desks = bookingDeskService.getBookingDesksByTop3();
        return new ResponseEntity<>(desks, HttpStatus.OK);
    }

    @GetMapping("/booking/bottom")
    public ResponseEntity<List<BookingDesk>> getBookingDesksByBottom() {
        List<BookingDesk> desks = bookingDeskService.getBookingDesksByBottom();
        return new ResponseEntity<>(desks, HttpStatus.OK);
    }

    @PutMapping("/booking/{id}")
    public ResponseEntity<BookingDesk> updateBookingDesk(@PathVariable("id") String id, @RequestBody BookingDesk updatedBookingDesk) {
        BookingDesk bookingDesk = bookingDeskService.updateBookingDesk(id, updatedBookingDesk);
        return new ResponseEntity<>(bookingDesk, HttpStatus.OK);
    }

    @PostMapping("/booking/{id}")
        public ResponseEntity<Integer> getBookingDesksById(@PathVariable("id") String id, @RequestBody BookingDeskDateRequest bookingDeskDateRequest) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDate = LocalDateTime.parse(bookingDeskDateRequest.getStart_date());
        LocalDateTime endDate = LocalDateTime.parse(bookingDeskDateRequest.getEnd_date());

        Integer desks = bookingDeskService.getBookingDeskById(id,startDate, endDate);
        return new ResponseEntity<>(desks, HttpStatus.OK);
    }

    @GetMapping("/booking")
    public ResponseEntity<List<HistoryResponseDTO>> getAllBookings() {
        List<BookingDesk> allBookings = bookingDeskService.getAllBookingDesks();
        List<HistoryResponseDTO> allHistories = allBookings.stream()
                .map(bookingDesk -> new HistoryResponseDTO(
                        bookingDesk.getUser().getUsername(),
                        bookingDesk.getDesk().getId(),
                        bookingDesk.getStartBookingTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        bookingDesk.getEndBookingTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                ))
                .toList();
        return new ResponseEntity<>(allHistories, HttpStatus.OK);
    }

    @GetMapping("/booking/username/{username}")
    public ResponseEntity<List<HistoryResponseDTO>> getBookingDeskByUserUsername(@PathVariable("username") String username) {
        List<BookingDesk> bookingsByUserUsername = bookingDeskService.findByUserUsername(username);
        List<HistoryResponseDTO> historiesByUserUsername = bookingsByUserUsername.stream()
                .map(bookingDesk -> new HistoryResponseDTO(
                        bookingDesk.getUser().getUsername(),
                        bookingDesk.getDesk().getId(),
                        bookingDesk.getStartBookingTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        bookingDesk.getEndBookingTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                ))
                .toList();
        return new ResponseEntity<>(historiesByUserUsername, HttpStatus.OK);
    }

    @PostMapping("/booking/day/{id}")
    public ResponseEntity<List<BookingDesk>> getAllBookingDesksByDate(@PathVariable("id") String id, @RequestBody BookingDeskDateRequest bookingDeskDateRequest) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDate = LocalDateTime.parse(bookingDeskDateRequest.getStart_date());
        LocalDateTime endDate = LocalDateTime.parse(bookingDeskDateRequest.getEnd_date());

        List<BookingDesk> desks = bookingDeskService.getAllBookingDesksByDate(id,startDate, endDate);
        return new ResponseEntity<>(desks, HttpStatus.OK);
    }
}
