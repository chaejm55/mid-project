package com.example.midproject.controller;

import com.example.midproject.dto.ResponseReservationDto;
import com.example.midproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/{userName}")
    public ResponseEntity<List<ResponseReservationDto>> getUserReservations(@PathVariable String userName) {
        List<ResponseReservationDto> reservationDtoList = userService.getUserReservations(userName).stream().map(ResponseReservationDto::new).toList();
        return ResponseEntity.ok().body(reservationDtoList);
    }
}
