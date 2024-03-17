package com.example.codemaster.service;

import com.example.codemaster.model.BookingDesk;
import com.example.codemaster.model.DTO.AddBookingDeskDTO;
import com.example.codemaster.model.Desk;
import com.example.codemaster.model.User;
import com.example.codemaster.repository.BookingDeskRepository;
import com.example.codemaster.repository.DeskRepository;
import com.example.codemaster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingDeskService {
    @Autowired
    private BookingDeskRepository bookingDeskRepository;
    @Autowired
    private DeskRepository deskRepository;
    @Autowired
    private UserRepository userRepository;

    public List<BookingDesk> getAllBookingDesks() {
        return bookingDeskRepository.findAll();
    }

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
        BookingDesk bookingDesk = bookingDeskRepository.findByDeskIdUnique(id)
                .orElseThrow(() -> new IllegalArgumentException("BookingDesk with id " + id + " does not exist."));

        bookingDesk.setStartBookingTime(updatedBookingDesk.getStartBookingTime());
        bookingDesk.setUser(updatedBookingDesk.getUser());

        return bookingDeskRepository.save(bookingDesk);
    }

    public Integer getBookingDeskById(String id, LocalDateTime startTime, LocalDateTime endTime) {
        List<BookingDesk> bookingDesks = bookingDeskRepository.findByDeskId(id, startTime, endTime);
        if(bookingDesks.isEmpty()){
            return 0;
        }
        else if(bookingDesks.size()<10){
            return 1;
        }else{
            return 2;
        }
    }

    public List<BookingDesk> getAllBookingDesksByDate(String id,LocalDateTime startTime,LocalDateTime endTime){
        List<BookingDesk> bookingDesks = bookingDeskRepository.findByDeskId(id, startTime, endTime);
        return bookingDesks;
    }

    public List<BookingDesk> findByUserUsername(String username) {
        return bookingDeskRepository.findByUserUsername(username);
    }

    public BookingDesk newBookingDesk(AddBookingDeskDTO addBookingDeskDTO) {
        Desk desk = deskRepository.findById(addBookingDeskDTO.getDeskId())
                .orElseThrow(() -> new IllegalArgumentException("Desk with id does not exist."));

        User user = userRepository.findByUsername(addBookingDeskDTO.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User with id does not exist."));

        LocalDateTime startDate = LocalDateTime.parse(addBookingDeskDTO.getStart_date());
        LocalDateTime endDate = LocalDateTime.parse(addBookingDeskDTO.getEnd_date());

        BookingDesk bookingDeskToSave = BookingDesk.builder()
                .desk(desk)
                .user(user)
                .startBookingTime(startDate)
                .endBookingTime(endDate)
                .build();

        return bookingDeskRepository.save(bookingDeskToSave);
    }
}
