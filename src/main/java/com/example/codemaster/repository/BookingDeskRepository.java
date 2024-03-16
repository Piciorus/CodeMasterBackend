package com.example.codemaster.repository;

import com.example.codemaster.model.BookingDesk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingDeskRepository extends JpaRepository<BookingDesk, String> {
    @Query("SELECT d FROM BookingDesk d " +
            "WHERE d.desk.id LIKE '1.%' OR d.desk.id LIKE '2.%' OR d.desk.id LIKE '3.%'")
    List<BookingDesk> findBookingDeskByLeft();

    @Query("SELECT d FROM BookingDesk d " +
            "WHERE d.desk.id LIKE '4.%' OR d.desk.id LIKE '5.%' OR d.desk.id LIKE '6.%' OR d.desk.id LIKE '7.%' OR d.desk.id LIKE '8.%' OR d.desk.id LIKE '9.%' OR d.desk.id LIKE '10.%' OR d.desk.id LIKE '11.%' OR d.desk.id LIKE '12.%' OR d.desk.id LIKE '13.%' OR d.desk.id LIKE '14.%'")
    List<BookingDesk> findBookingDeskByTop();

    @Query("SELECT d FROM BookingDesk d " +
            "WHERE d.desk.id LIKE '15.%' OR d.desk.id LIKE '16.%'")
    List<BookingDesk> findBookingDeskByTop2();

    @Query("SELECT d FROM BookingDesk d " +
            "WHERE d.desk.id LIKE '17.%' OR d.desk.id LIKE '18.%' OR d.desk.id LIKE '19.%' OR d.desk.id LIKE '20.%' OR d.desk.id LIKE '21.%' OR d.desk.id LIKE '22.%' OR d.desk.id LIKE '23.%'")
    List<BookingDesk> findBookingDeskByTop3();

    @Query("SELECT d FROM BookingDesk d " +
            "WHERE d.desk.id LIKE '24.%' OR d.desk.id LIKE '25.%' OR d.desk.id LIKE '26.%' OR d.desk.id LIKE '27.%' OR d.desk.id LIKE '28.%' OR d.desk.id LIKE '29.%' OR d.desk.id LIKE '30.%' OR d.desk.id LIKE '31.%' OR d.desk.id LIKE '32.%' OR d.desk.id LIKE '33.%'")
    List<BookingDesk> findBookingDeskByBottom();

    @Query("SELECT bd FROM BookingDesk bd WHERE bd.desk.id = :deskId")
    Optional<BookingDesk> findByDeskId(String deskId);

    @Query("SELECT bd FROM BookingDesk bd WHERE bd.user.username = :username")
    List<BookingDesk> findByUserUsername(String username);
}
