package com.example.midproject.entity;

import com.example.midproject.dto.UpdateReservationDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "reservations")
public class Reservation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Long roomNumber;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Builder
    public Reservation(Long id, Long roomNumber, Status status, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void update(UpdateReservationDto updateReservationDto) {
        this.roomNumber = updateReservationDto.getRoomNumber();
        this.startTime = updateReservationDto.getStartTime();
        this.endTime = updateReservationDto.getEndTime();
    }

    public void updateStatus(Status status) {
        this.status = status;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
