package com.example.midproject.dto;

import com.example.midproject.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.example.midproject.entity.Status.PENDING;

@NoArgsConstructor
@Getter
public class CreateReservationDto {
    private String userName;
    private Long roomNumber;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Reservation toEntity() {
        return Reservation.builder()
                .roomNumber(roomNumber)
                .startTime(startTime)
                .endTime(endTime)
                .status(PENDING)
                .build();
    }
}
