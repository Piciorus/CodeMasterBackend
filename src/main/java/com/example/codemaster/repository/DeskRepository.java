package com.example.codemaster.repository;

import com.example.codemaster.model.Desk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeskRepository extends JpaRepository<Desk, String> {
    @Query("SELECT d FROM Desk d " +
            "WHERE d.id LIKE '1.%' OR d.id LIKE '2.%' OR d.id LIKE '3.%'")
    List<Desk> findLeftDesks();

    @Query("SELECT d.capacity FROM Desk d " +
            "WHERE d.id = :id")
    Long getRoomCapacity(String id);

    @Query("SELECT d FROM Desk d " +
            "WHERE d.id LIKE '4.%' OR d.id LIKE '5.%' OR d.id LIKE '6.%' OR d.id LIKE '7.%' OR d.id LIKE '8.%' OR d.id LIKE '9.%' OR d.id LIKE '10.%' OR d.id LIKE '11.%' OR d.id LIKE '12.%' OR d.id LIKE '13.%' OR d.id LIKE '14.%'")
    List<Desk> findTop4Desks();
}
