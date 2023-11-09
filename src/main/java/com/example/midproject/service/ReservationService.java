package com.example.midproject.service;

import com.example.midproject.dto.CreateReservationDto;
import com.example.midproject.dto.UpdateReservationDto;
import com.example.midproject.entity.Reservation;
import com.example.midproject.entity.Status;
import com.example.midproject.entity.Role;
import com.example.midproject.entity.User;
import com.example.midproject.repository.ReservationRepository;
import com.example.midproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createReservation(CreateReservationDto createReservationDto) {
        if (!isReservationAvailable(createReservationDto.getRoomNumber(), createReservationDto.getStartTime(), createReservationDto.getEndTime())) {
            throw new IllegalArgumentException("해당 시간에는 예약이 불가능합니다.");
        }
        User user = userRepository.save(new User(createReservationDto.getUserName(), Role.USER));
        Reservation reservation = reservationRepository.save(createReservationDto.toEntity());
        reservation.setUser(user);
    }

    public Reservation getReservation(Long reservationId) {
       return reservationRepository.findById(reservationId).orElseThrow(
               () -> new NoSuchElementException("해당 예약이 없습니다.")
       );
    }

    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }

    @Transactional
    public void updateReservation(Long reservationId, UpdateReservationDto updateReservationDto) {
        Reservation updateReservation = reservationRepository.findById(reservationId).orElseThrow(
                () -> new NoSuchElementException("해당 예약이 없습니다.")
        );
        if (!isReservationAvailable(updateReservationDto.getRoomNumber(), updateReservationDto.getStartTime(), updateReservationDto.getEndTime())) {
            throw new IllegalArgumentException("해당 시간에는 예약이 불가능합니다.");
        }
    }

    @Transactional
    public void deleteReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(
                () -> new NoSuchElementException("해당 예약이 없습니다.")
        );
        if (reservation.getStatus() == Status.RESERVED || reservation.getStatus() == Status.DONE) {
            new IllegalArgumentException("이미 완료된 예약은 삭제할 수 없습니다.");
        }
        reservationRepository.deleteById(reservationId);
    }

    @Transactional
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(
                () -> new NoSuchElementException("해당 예약이 없습니다.")
        );
        if (reservation.getStatus() == Status.CANCELLED) {
            new IllegalArgumentException("이미 취소된 예약입니다.");
        }
        reservation.updateStatus(Status.CANCELLED);
    }

    @Transactional
    public void approveReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(
                () -> new NoSuchElementException("해당 예약이 없습니다.")
        );
        if (reservation.getStatus() == Status.RESERVED) {
            new IllegalArgumentException("이미 승인된 예약입니다.");
        }
        reservation.updateStatus(Status.RESERVED);
    }
    @Transactional
    public void denyReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(
                () -> new NoSuchElementException("해당 예약이 없습니다.")
        );
        if (reservation.getStatus() == Status.DENIED) {
            new IllegalArgumentException("이미 거절된 예약입니다.");
        }
        reservation.updateStatus(Status.DENIED);
    }

    private boolean isReservationAvailable(Long roomNumber, LocalDateTime startTime, LocalDateTime endTime) {
        List<Reservation> conflictingReservations = reservationRepository.findConflictingReservations(roomNumber.toString(), startTime, endTime);

        return conflictingReservations.isEmpty();
    }
}
