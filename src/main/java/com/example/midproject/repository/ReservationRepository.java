package com.example.midproject.repository;

import com.example.midproject.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r " +
            "WHERE r.roomNumber = :roomNumber " +
            "AND ((:startTime BETWEEN r.startTime AND r.endTime) OR " +
            "(:endTime BETWEEN r.startTime AND r.endTime))")
    List<Reservation> findConflictingReservations(
            @Param("roomNumber") String roomNumber,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );
}
