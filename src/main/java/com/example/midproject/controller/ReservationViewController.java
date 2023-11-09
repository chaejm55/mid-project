package com.example.midproject.controller;

import com.example.midproject.dto.ResponseReservationDto;
import com.example.midproject.entity.Reservation;
import com.example.midproject.entity.User;
import com.example.midproject.service.ReservationService;
import com.example.midproject.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ReservationViewController {
    private final ReservationService reservationService;
    private final UserService userService;

    @GetMapping("/new-reservation")
    public String newReservation(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("reservation", new ResponseReservationDto());
        } else {
            Reservation reservation = reservationService.getReservation(id);
            model.addAttribute("reservation", new ResponseReservationDto(reservation));
        }
        return "newReservation";
    }

    @GetMapping("/reservation/{id}")
    public String showReservation(@PathVariable Long id, Model model) {
        Reservation reservation = reservationService.getReservation(id);
        User user = reservation.getUser();
        model.addAttribute("reservation", new ResponseReservationDto(reservation));
        model.addAttribute("user", user);
        return "reservation";
    }

    @GetMapping("/reservations/{userName}")
    public String showUserReservations(@PathVariable String userName, Model model) {
        List<ResponseReservationDto> reservationDtoList = userService.getUserReservations(userName).stream().map(ResponseReservationDto::new).toList();
        User user = userService.getUser(userName);
        model.addAttribute("reservations", reservationDtoList);
        model.addAttribute("user", user);
        return "reservationList";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
