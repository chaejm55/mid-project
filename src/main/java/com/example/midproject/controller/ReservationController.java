package com.example.midproject.controller;

import com.example.midproject.dto.CreateReservationDto;
import com.example.midproject.dto.ResponseReservationDto;
import com.example.midproject.dto.UpdateReservationDto;
import com.example.midproject.entity.Reservation;
import com.example.midproject.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/create")
    public ResponseEntity createReservation(@RequestBody CreateReservationDto createReservationDto) {
        reservationService.createReservation(createReservationDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<ResponseReservationDto> getReservation(@PathVariable Long reservationId) {
        ResponseReservationDto reservationDto = new ResponseReservationDto(reservationService.getReservation(reservationId));
        return ResponseEntity.ok().body(reservationDto);
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity updateReservation(@PathVariable Long reservationId, @RequestBody UpdateReservationDto updateReservationDto) {
        reservationService.updateReservation(reservationId, updateReservationDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity deleteReservation(@PathVariable Long reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cancel/{reservationId}")
    public ResponseEntity cancelReservation(@PathVariable Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/approve/{reservationId}")
    public ResponseEntity approveReservation(@PathVariable Long reservationId) {
        reservationService.approveReservation(reservationId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/deny/{reservationId}")
    public ResponseEntity denyReservation(@PathVariable Long reservationId) {
        reservationService.denyReservation(reservationId);
        return ResponseEntity.ok().build();
    }
}
