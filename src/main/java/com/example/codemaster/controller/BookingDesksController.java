package com.example.codemaster.controller;

import com.example.codemaster.model.BookingDesk;
import com.example.codemaster.service.BookingDeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
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
}
