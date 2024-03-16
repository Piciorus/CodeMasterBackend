package com.example.codemaster.service;

import com.example.codemaster.model.BookingDesk;
import com.example.codemaster.model.Desk;
import com.example.codemaster.repository.BookingDeskRepository;
import com.example.codemaster.repository.DeskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingDeskService {
    @Autowired
    private BookingDeskRepository bookingDeskRepository;

    public List<BookingDesk> getBookingDeskByLeft() {
        return bookingDeskRepository.findBookingDeskByLeft();
    }

    public List<BookingDesk> getBookingDesksByTop() {
        return bookingDeskRepository.findBookingDeskByTop();
    }

    public List<BookingDesk> getBookingDesksByTop2() {
        return bookingDeskRepository.findBookingDeskByTop2();
    }

    public List<BookingDesk> getBookingDesksByTop3() {
        return bookingDeskRepository.findBookingDeskByTop3();
    }

    public List<BookingDesk> getBookingDesksByBottom() {
        return bookingDeskRepository.findBookingDeskByBottom();
    }

    public BookingDesk updateBookingDesk(String id, BookingDesk updatedBookingDesk) {
        BookingDesk bookingDesk = bookingDeskRepository.findByDeskId(id)
                .orElseThrow(() -> new IllegalArgumentException("BookingDesk with id " + id + " does not exist."));

        bookingDesk.setBookingTime(updatedBookingDesk.getBookingTime());
        bookingDesk.setUser(updatedBookingDesk.getUser());

        return bookingDeskRepository.save(bookingDesk);
    }
}
