package com.example.midproject.dto;

import com.example.midproject.entity.Reservation;
import com.example.midproject.entity.Status;
import jdk.jfr.Name;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ResponseReservationDto {
    private Long id;
    private String userName;
    private Long roomNumber;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status;
    private LocalDateTime createdAt;
    public ResponseReservationDto(Reservation reservation) {
        this.id = reservation.getId();
        this.userName = reservation.getUser().getName();
        this.roomNumber = reservation.getRoomNumber();
        this.startTime = reservation.getStartTime();
        this.endTime = reservation.getEndTime();
        this.status = reservation.getStatus();
        this.createdAt = reservation.getCreatedAt();
    }
}
