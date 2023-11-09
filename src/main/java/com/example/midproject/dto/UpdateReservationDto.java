package com.example.midproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UpdateReservationDto {
    private Long roomNumber;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
